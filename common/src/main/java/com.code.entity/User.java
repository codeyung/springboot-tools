package com.code.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.17:14
 */
@Getter
@Setter
@ToString
public class User {


    private long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 性别 0男1女
     */
    private int gender;


}
