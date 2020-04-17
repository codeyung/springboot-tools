package com.logic.spring.boot.annotation;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-10.17:02
 */
@FunctionalInterface
public interface CheckedFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}
