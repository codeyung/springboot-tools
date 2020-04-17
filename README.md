springboot-tools
====

springboot 项目中常用的工具集



## 1. 模块描述

 模块中文名|模块英文名|模块内容
 ---- | ----- | ------ 
 公用模块|common|通用工具-基本对象、工具类
 缓存|cache|缓存内容-springcache redis guava caffeine
 数据源动态路由|dynamic-data-source|常遇到的多数据源、或相同表结构不同库等情况
 事务扩展|transaction-extension|需要在核心事物提交前、后做某些操作（强一致性操作or最终一致）
 逻辑扩展增强自动配置|logic-spring-boot-autoconfigure|减少代码中逻辑判断使用自定义条件对象、函数式调用方法、支持配置
 逻辑扩展增强依赖|logic-spring-boot-starter|逻辑扩展增强依赖
 逻辑扩展增强示例|logic-spring-boot-starter-example|逻辑扩展增强示例
 代码生成组件|generator|使用模板与myabtis扩展组件根据库表生成代码已经页面相关
 ...持续添加


## 2. 使用的框架方案

* spring-boot 2.2.6.RELEASE
* lombok

## 3. logic-spring-boot-starter 使用方式

#### pom.xml
```xml
<dependencies>
    <dependency>
        <groupId>com.example.springboot</groupId>
        <artifactId>logic-spring-boot-starter</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
 ```
 #### 定义逻辑注解 @interface
```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Logic {

    String group() default LogicCondition.LOGIC_GROUP;
    int type() default LogicCondition.LOGIC_TYPE;
}
```
#### 创建逻辑注解的对象 Condition 覆盖equals与hashCode 后续有需要优化为注解生成的唯一值 删掉此对象
```java
public class LogicCondition {

    public static final String LOGIC_GROUP = "default";
    public static final int LOGIC_TYPE = 0;

    private String group;
    private int type;


    public LogicCondition() {
    }

    public LogicCondition(String group, int type) {
        this.group = group;
        this.type = type;
    }
    //...getter and setter...

    public static LogicCondition newDefault() {
        return new LogicCondition(LOGIC_GROUP, LOGIC_TYPE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogicCondition that = (LogicCondition) o;

        if (!Objects.equals(group, that.group)) {
            return false;
        }

        if (type != that.type) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, type);
    }

    @Override
    public String toString() {
        return "LogicCondition{" +
                "group='" + group + '\'' +
                ", type=" + type +
                '}';
    }


}
```
#### application.yml or application.properties map类型 key:value （逻辑注解:逻辑对象）
```yaml
logic:
  extensions:
    com.codeyung.test.annotation.Logic: com.codeyung.test.annotation.LogicCondition
```
#### 定义接口 逻辑扩展的interface 继承 LogicFunction
```java
//无返回值
public interface VoidFunction extends LogicFunction {
    void puls(int var);
}
//无返回值需要捕获异常
public interface CheckedVoidFunction extends LogicFunction {
    void puls(int var) throws Exception;
}
//带返回值
public interface ReturnFunction extends LogicFunction {
    int puls(int var);
}
//带返回值需要捕获异常
public interface CheckedReturnFunction extends LogicFunction {
    int puls(int var) throws Exception;
}
```
#### 默认实现 default
```java
@Service
@Logic
public class CheckedReturnDefault implements CheckedReturnFunction {

    @Override
    public int puls(int var) throws Exception {
        return var;
    }
}
```
#### 扩展实现 one
```java
@Service
@Logic(group = "one", type = 1)
public class CheckedVoidOne implements CheckedVoidFunction {

    @Override
    public void puls(int var)throws Exception {
        System.out.println("CheckedVoidOne");
    }

}
```
#### 扩展实现 two
```java
@Service
@Logic(group = "two", type = 2)
public class CheckedReturnTwo implements CheckedReturnFunction {

    @Override
    public int puls(int var) throws Exception {
        throw new RuntimeException("CheckedReturnTwo Exception");
//        return var;
    }
}
```
#### 测试用例
```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestStarterApplication.class)
public class TestLogicFunction {

    @Autowired
    `private LogicExecutor logicExecutor;`

    @Test
    public void testVoid() {
        System.out.println("------------------testVoid start ----------------");
        String str = "condition:{0},executeVoid";

        LogicCondition condition = LogicCondition.newDefault();
        String str1 = MessageFormat.format(str, condition);
        System.out.println(str1);
        `logicExecutor.executeVoid(VoidFunction.class, condition, function -> function.puls(0))`;

        condition = new LogicCondition("one", 1);
        str1 = MessageFormat.format(str, condition);
        System.out.println(str1);
        logicExecutor.executeVoid(VoidFunction.class, condition, function -> function.puls(1));

        condition = new LogicCondition("two", 2);
        str1 = MessageFormat.format(str, condition);
        System.out.println(str1);
        logicExecutor.executeVoid(VoidFunction.class, condition, function -> function.puls(2));
    }


    @Test
    public void testCheckedFunc() {
        System.out.println("------------------testCheckedFunc start ----------------");
        String str = "condition:{0},executeVoidCheckedFunc";
        try {
            LogicCondition condition = LogicCondition.newDefault();
            String str1 = MessageFormat.format(str, condition);
            System.out.println(str1);
            `logicExecutor.executeVoidCheckedFunc(CheckedVoidFunction.class, condition, function -> function.puls(0))`;

            condition = new LogicCondition("one", 1);
            str1 = MessageFormat.format(str, condition);
            System.out.println(str1);
            logicExecutor.executeVoidCheckedFunc(CheckedVoidFunction.class, condition, function -> function.puls(1));

            condition = new LogicCondition("two", 2);
            str1 = MessageFormat.format(str, condition);
            System.out.println(str1);
            logicExecutor.executeVoidCheckedFunc(CheckedVoidFunction.class, condition, function -> function.puls(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReturn() {
        System.out.println("------------------testReturn start ----------------");
        String str = "condition:{0},result:{1},execute";

        LogicCondition condition = LogicCondition.newDefault();
        int result = `logicExecutor.execute(ReturnFunction.class, condition, function -> function.puls(0))`;
        String str1 = MessageFormat.format(str, condition, result);
        System.out.println(str1);

        condition = new LogicCondition("one", 1);
        result = logicExecutor.execute(ReturnFunction.class, condition, function -> function.puls(1));
        str1 = MessageFormat.format(str, condition, result);
        System.out.println(str1);

        condition = new LogicCondition("two", 2);
        result = logicExecutor.execute(ReturnFunction.class, condition, function -> function.puls(2));
        str1 = MessageFormat.format(str, condition, result);
        System.out.println(str1);
    }


    @Test
    public void testCheckedReturn(){
        System.out.println("------------------testCheckedReturn start ----------------");
        String str = "condition:{0},result:{1},executeCheckedFunc";
        try {
            LogicCondition condition = LogicCondition.newDefault();
            int result = `logicExecutor.executeCheckedFunc(CheckedReturnFunction.class, condition, function -> function.puls(1))`;
            String str1 = MessageFormat.format(str, condition, result);
            System.out.println(str1);

            condition = new LogicCondition("one", 1);
            result = logicExecutor.executeCheckedFunc(CheckedReturnFunction.class, condition, function -> function.puls(1));
            str1 = MessageFormat.format(str, condition, result);
            System.out.println(str1);

            condition = new LogicCondition("two", 2);
            result = logicExecutor.executeCheckedFunc(CheckedReturnFunction.class, condition, function -> function.puls(2));
            str1 = MessageFormat.format(str, condition, result);
            System.out.println(str1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
```
#### 返回结果
```
------------------testVoid start ----------------
condition:LogicCondition{group='default', type=0},executeVoid
VoidDefault
condition:LogicCondition{group='one', type=1},executeVoid
VoidOne
condition:LogicCondition{group='two', type=2},executeVoid
VoidTwo

------------------testCheckedFunc start ----------------
condition:LogicCondition{group='default', type=0},executeVoidCheckedFunc
CheckedVoidDefault
condition:LogicCondition{group='one', type=1},executeVoidCheckedFunc
CheckedVoidOne
condition:LogicCondition{group='two', type=2},executeVoidCheckedFunc
java.lang.RuntimeException: CheckedVoidTwo Exception

------------------testReturn start ----------------
condition:LogicCondition{group='default', type=0},result:0,execute
condition:LogicCondition{group='one', type=1},result:1,execute
condition:LogicCondition{group='two', type=2},result:2,execute

------------------testCheckedReturn start ----------------
condition:LogicCondition{group='default', type=0},result:1,executeCheckedFunc
condition:LogicCondition{group='one', type=1},result:1,executeCheckedFunc
java.lang.RuntimeException: CheckedReturnTwo Exception
```