package com.codeyung.test.logic.impl;

import com.codeyung.test.annotation.Logic;
import com.codeyung.test.logic.VoidFunction;
import org.springframework.stereotype.Service;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@Logic(group = "two", type = 2)
public class VoidTwo implements VoidFunction {
    @Override
    public void puls(int var) {
        System.out.println("VoidTwo");
    }
}
