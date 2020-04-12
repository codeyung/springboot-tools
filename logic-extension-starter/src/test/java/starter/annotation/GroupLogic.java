package starter.annotation;

import java.lang.annotation.*;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.00:20
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GroupLogic {

    String group() default LogicCondition.LOGIC_GROUP;
}
