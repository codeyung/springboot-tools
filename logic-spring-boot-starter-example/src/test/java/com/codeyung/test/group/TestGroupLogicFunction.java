package com.codeyung.test.group;

import com.codeyung.test.TestStarterApplication;
import com.codeyung.test.annotation.GroupLogicCondition;
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
public class TestGroupLogicFunction {

    @Autowired
    private LogicExecutor logicExecutor;


    @Test
    public void testFunction() {
        GroupLogicCondition condition = GroupLogicCondition.newDefault();
        logicExecutor.executeVoid(TestGroupVoidFunction.class, condition, function -> function.group(0));

        condition = new GroupLogicCondition("one");
        logicExecutor.executeVoid(TestGroupVoidFunction.class, condition, function -> function.group(1));

        condition = GroupLogicCondition.newDefault();
        int result = logicExecutor.execute(TestGroupReturnFunction.class, condition, function -> function.group(0));
        System.out.println(result);
        condition = new GroupLogicCondition("one");
        result = logicExecutor.execute(TestGroupReturnFunction.class, condition, function -> function.group(1));
        System.out.println(result);


    }

}
