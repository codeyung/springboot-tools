package com.codeyung.test.logic;

import com.codeyung.test.TestStarterApplication;
import com.codeyung.test.annotation.LogicCondition;
import com.logic.spring.boot.autoconfigure.LogicExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.18:32
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestStarterApplication.class)
public class TestLogicFunction {

    @Autowired
    private LogicExecutor logicExecutor;


    @Test
    public void testVoid() {
        LogicCondition condition = LogicCondition.newDefault();
        logicExecutor.executeVoid(TestVoidFunction.class, condition, function -> function.puls(0));

        condition = new LogicCondition("one", 1);
        logicExecutor.executeVoid(TestVoidFunction.class, condition, function -> function.puls(1));

        condition = new LogicCondition("two", 2);
        logicExecutor.executeVoid(TestVoidFunction.class, condition, function -> function.puls(2));

        condition = LogicCondition.newDefault();
        int result = logicExecutor.execute(TestReturnFunction.class, condition, function -> function.puls(0));
        System.out.println(result);
        condition = new LogicCondition("one", 1);
        result = logicExecutor.execute(TestReturnFunction.class, condition, function -> function.puls(1));
        System.out.println(result);

        condition = new LogicCondition("two", 2);
        result = logicExecutor.execute(TestReturnFunction.class, condition, function -> function.puls(2));
        System.out.println(result);


    }


    @Test
    public void testCheckedFunc() throws Exception {
        LogicCondition condition = LogicCondition.newDefault();
        logicExecutor.executeVoidCheckedFunc(TestVoidFunction.class, condition, function -> function.puls(0));

        condition = new LogicCondition("one", 1);
        logicExecutor.executeVoidCheckedFunc(TestVoidFunction.class, condition, function -> function.puls(1));

        condition = new LogicCondition("two", 2);
        logicExecutor.executeVoidCheckedFunc(TestVoidFunction.class, condition, function -> function.puls(2));
    }

    @Test
    public void testReturn() throws Exception {
        LogicCondition condition = LogicCondition.newDefault();
        int result = logicExecutor.execute(TestReturnFunction.class, condition, function -> function.puls(0));
        System.out.println(result);
        condition = new LogicCondition("one", 1);
        result = logicExecutor.execute(TestReturnFunction.class, condition, function -> function.puls(1));
        System.out.println(result);

        condition = new LogicCondition("two", 2);
        result = logicExecutor.execute(TestReturnFunction.class, condition, function -> function.puls(2));
        System.out.println(result);
    }


    @Test
    public void testCheckedReturn() throws Exception {
        LogicCondition condition = LogicCondition.newDefault();
        int result = logicExecutor.executeCheckedFunc(TestReturnFunction.class, condition, function -> function.puls(1));
        System.out.println(result);

        condition = new LogicCondition("one", 1);
        result = logicExecutor.executeCheckedFunc(TestReturnFunction.class, condition, function -> function.puls(1));
        System.out.println(result);

        condition = new LogicCondition("two", 2);
        result = logicExecutor.executeCheckedFunc(TestReturnFunction.class, condition, function -> function.puls(2));
        System.out.println(result);
    }


}
