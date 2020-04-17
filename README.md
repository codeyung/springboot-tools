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

### pom.xml
```xml
<dependencies>
    <dependency>
        <groupId>com.example.springboot</groupId>
        <artifactId>logic-spring-boot-starter</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
 ```
 ### 定义逻辑注解 @interface
```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GroupLogic {

    String group() default LogicCondition.LOGIC_GROUP;
}
```
 ### 创建逻辑注解的对象 Condition 覆盖equals与hashCode 后续有需要优化为注解生成的唯一值 删掉此对象
```java
public class GroupLogicCondition {

    public static final String LOGIC_GROUP = "default";

    private String group;


    public GroupLogicCondition() {
    }

    public GroupLogicCondition(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public static GroupLogicCondition newDefault() {
        return new GroupLogicCondition(LOGIC_GROUP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupLogicCondition that = (GroupLogicCondition) o;

        if (!Objects.equals(group, that.group)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(group);
    }

    @Override
    public String toString() {
        return "LogicCondition{" +
                "group='" + group + '\'' +
                '}';
    }

}
```
### application.yml or application.properties map类型 key:value （逻辑注解:逻辑对象）
```yaml
logic:
  extensions:
    com.codeyung.test.annotation.GroupLogic: com.codeyung.test.annotation.GroupLogicCondition
```
 ### 定义接口 逻辑扩展的interface 继承 LogicFunction
```java
public interface TestGroupReturnFunction extends LogicFunction {
    int group(int var);
}
```
 ### 默认实现 default
```java
@Service
@GroupLogic
public class TestGroupReturnDefault implements TestGroupReturnFunction {


    @Override
    public int group(int var) {
        System.out.println("TestGroupReturnDefault");
        return var;
    }

}
```
 ### 扩展实现 one
```java
@Service
@GroupLogic(group = "one")
public class TestGroupReturnOne implements TestGroupReturnFunction {

    @Override
    public int group(int var) {
        System.out.println("TestReturnReturnOne");
        return var;
    }
}
```
 ### 测试用例
```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestStarterApplication.class)
public class TestGroupLogicFunction {

    @Autowired
    private LogicExecutor logicExecutor;


    @Test
    public void testFunction() {
        GroupLogicCondition condition = GroupLogicCondition.newDefault();
        int result = logicExecutor.execute(TestGroupReturnFunction.class, condition, function -> function.group(0));
        System.out.println(result);
        condition = new GroupLogicCondition("one");
        result = logicExecutor.execute(TestGroupReturnFunction.class, condition, function -> function.group(1));
        System.out.println(result);
    }

}
```
 ### 返回结果
```
newDefault : 0
one : 1
```