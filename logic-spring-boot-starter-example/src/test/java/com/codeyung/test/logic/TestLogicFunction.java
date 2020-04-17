package com.codeyung.test.logic;

import com.codeyung.test.TestStarterApplication;
import com.codeyung.test.annotation.LogicCondition;
import com.logic.spring.boot.autoconfigure.LogicExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;


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
        System.out.println("------------------testVoid start ----------------");
        String str = "condition:{0},executeVoid";

        LogicCondition condition = LogicCondition.newDefault();
        String str1 = MessageFormat.format(str, condition);
        System.out.println(str1);
        logicExecutor.executeVoid(VoidFunction.class, condition, function -> function.puls(0));

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
            logicExecutor.executeVoidCheckedFunc(CheckedVoidFunction.class, condition, function -> function.puls(0));

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
        int result = logicExecutor.execute(ReturnFunction.class, condition, function -> function.puls(0));
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
            int result = logicExecutor.executeCheckedFunc(CheckedReturnFunction.class, condition, function -> function.puls(1));
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
