package starter.group.impl;

import org.springframework.stereotype.Service;
import starter.annotation.GroupLogic;
import starter.group.TestGroupReturnFunction;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@GroupLogic(group = "one")
public class TestGroupReturnOne implements TestGroupReturnFunction {

    @Override
    public int group(int var) {
        System.out.println("TestReturnReturnOne");
        return var;
    }
}
