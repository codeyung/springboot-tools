package auto;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.03:02
 */
public class LogicCoordinate {


    private Class logicFunctionClass;
    private String logicFunctionName;
    private LogicCondition condition;
    private int uniqueIdentity;


    public LogicCoordinate(Class logicFunctionClass, LogicCondition condition) {
        this.logicFunctionClass = logicFunctionClass;
        this.logicFunctionName = calculateLogicFunction(logicFunctionClass);
        this.condition = condition;
        this.uniqueIdentity = this.condition.hashCode();
    }

    public LogicCoordinate(String logicFunctionName, LogicCondition condition) {
        this.logicFunctionName = logicFunctionName;
        this.condition = condition;
        this.uniqueIdentity = this.condition.hashCode();
    }

    public String getLogicFunctionName() {
        return logicFunctionName;
    }

    public void setLogicFunctionName(String logicFunctionName) {
        this.logicFunctionName = logicFunctionName;
    }

    public int getUniqueIdentity() {
        return uniqueIdentity;
    }

    public void setUniqueIdentity(int uniqueIdentity) {
        this.uniqueIdentity = uniqueIdentity;
    }

    public Class getLogicFunctionClass() {
        return logicFunctionClass;
    }

    public void setLogicFunctionClass(Class logicFunctionClass) {
        this.logicFunctionClass = logicFunctionClass;
    }

    public LogicCondition getCondition() {
        return condition;
    }

    public void setCondition(LogicCondition condition) {
        this.condition = condition;
    }

    private String calculateLogicFunction(Class<?> targetClz) {
        Class<?> targetClass = targetClz;
        do {
            Class[] interfaces = targetClass.getInterfaces();
            if (ArrayUtils.isEmpty(interfaces)) {
                continue;
            }
            for (Class intf : interfaces) {
                String logicFunction = intf.getSimpleName();
                return intf.getName();
            }
            targetClass = targetClass.getSuperclass();
        } while (targetClass != null && targetClass != Object.class);

        throw new RuntimeException("LogicCoordinate Your name of LogicFunction for " + targetClz + " is not valid");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogicCoordinate that = (LogicCoordinate) o;

        if (uniqueIdentity != that.uniqueIdentity) {
            return false;
        }

        if (!Objects.equals(logicFunctionName, that.logicFunctionName)) {
            return false;
        }

        if (!Objects.equals(condition, that.condition)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(logicFunctionName, condition, uniqueIdentity);
    }

    @Override
    public String toString() {
        return "LogicCoordinate{" +
                "logicFunctionClass=" + logicFunctionClass +
                ", logicFunctionName='" + logicFunctionName + '\'' +
                ", condition=" + condition +
                ", uniqueIdentity=" + uniqueIdentity +
                ", hashCode=" + hashCode() +
                '}';
    }


    public static void main(String[] args) {
        LogicCondition condition = LogicCondition.newDefault();
        LogicCoordinate a = new LogicCoordinate(String.class, condition);
        LogicCoordinate b = new LogicCoordinate("java.io.Serializable", condition);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode() == b.hashCode());

        HashMap<LogicCoordinate, String> map = new HashMap<>();
        map.put(a, "111");
        map.put(b, "222");

        for (LogicCoordinate key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + ":" + value);
        }
    }


}
