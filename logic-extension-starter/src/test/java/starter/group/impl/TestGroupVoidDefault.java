package starter.group.impl;

import org.springframework.stereotype.Service;
import starter.annotation.GroupLogic;
import starter.group.TestGroupVoidFunction;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@GroupLogic
public class TestGroupVoidDefault implements TestGroupVoidFunction {

    @Override
    public void group(int var) {
        System.out.println("TestGroupVoidDefault");
    }

}
