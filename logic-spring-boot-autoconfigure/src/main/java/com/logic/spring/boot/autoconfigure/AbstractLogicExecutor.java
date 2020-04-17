package com.logic.spring.boot.autoconfigure;


import com.logic.spring.boot.annotation.CheckedConsumer;
import com.logic.spring.boot.annotation.CheckedFunction;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-10.17:05
 */
public abstract class AbstractLogicExecutor {


    public <R, T> R execute(Class<T> targetClz, Object condition, Function<T, R> exeFunction) {
        T logic = getFunction(targetClz, condition);
        return exeFunction.apply(logic);
    }

    public <R, T, E extends Exception> R executeCheckedFunc(Class<T> targetClz, Object condition, CheckedFunction<T, R, E> exeFunction) throws E {
        T logic = getFunction(targetClz, condition);
        return exeFunction.apply(logic);
    }

    public <T> void executeVoid(Class<T> targetClz, Object condition, Consumer<T> exeFunction) {
        T logic = getFunction(targetClz, condition);
        exeFunction.accept(logic);
    }

    public <T, E extends Exception> void executeVoidCheckedFunc(Class<T> targetClz, Object condition, CheckedConsumer<T, E> exeFunction) throws E {
        T logic = getFunction(targetClz, condition);
        exeFunction.accept(logic);
    }

    protected abstract <Func> Func getFunction(Class<Func> targetClz, Object condition);
}
