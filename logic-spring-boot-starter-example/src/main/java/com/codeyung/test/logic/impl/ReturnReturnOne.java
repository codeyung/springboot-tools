package com.codeyung.test.logic.impl;

import com.codeyung.test.annotation.Logic;
import com.codeyung.test.logic.ReturnFunction;
import org.springframework.stereotype.Service;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@Logic(group = "one", type = 1)
public class ReturnReturnOne implements ReturnFunction {
    @Override
    public int puls(int var) {
        return var;
    }
}
