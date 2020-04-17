package com.codeyung.test.group.impl;

import com.codeyung.test.annotation.GroupLogic;
import com.codeyung.test.group.TestGroupVoidFunction;
import org.springframework.stereotype.Service;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@GroupLogic(group = "one")
public class TestGroupVoidOne implements TestGroupVoidFunction {

    @Override
    public void group(int var) {
        System.out.println("TestGroupVoidOne");
    }

}
