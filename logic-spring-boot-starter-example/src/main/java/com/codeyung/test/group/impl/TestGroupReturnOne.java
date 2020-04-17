package com.codeyung.test.group.impl;

import com.codeyung.test.annotation.GroupLogic;
import com.codeyung.test.group.TestGroupReturnFunction;
import org.springframework.stereotype.Service;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@GroupLogic(group = "one")
public class TestGroupReturnOne implements TestGroupReturnFunction {

    @Override
    public int group(int var) {
        return var;
    }
}
