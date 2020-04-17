package com.codeyung.test.logic.impl;

import com.codeyung.test.annotation.Logic;
import com.codeyung.test.logic.TestVoidFunction;
import org.springframework.stereotype.Service;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@Logic
public class TestVoidDefault implements TestVoidFunction {

    @Override
    public void puls(int var) {
        System.out.println("TestVoidDefault");
    }

}