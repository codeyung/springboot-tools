package com.codeyung.test.logic;


import com.logic.spring.boot.annotation.LogicFunction;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:19
 */
public interface CheckedVoidFunction extends LogicFunction {
    void puls(int var) throws Exception;
}
