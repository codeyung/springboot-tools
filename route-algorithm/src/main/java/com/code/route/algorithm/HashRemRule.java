package com.code.route.algorithm;

import java.util.Random;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-11-15.17:16
 */
public class HashRemRule {


    /**
     * 取余 可设置切量范围
     *
     * @param args
     */
    public static void main(String[] args) {
        //这里的返回值可正可负

        int hashcode = new Random().nextInt();
        System.out.println("hashcode : " + hashcode);
        //效率高，任意数都会变为正
        int result = hashcode & Integer.MAX_VALUE;
        System.out.println("result : " + result);

        //得到一个0、1、2的任意数，此余数与切量范围匹配
        int num = result % 3;
        System.out.println("num : " + num);


    }
}
