package com.code.route.algorithm;

import java.util.regex.Pattern;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-11-15.17:40
 */
public class RegexRule {


    /**
     * 正则匹配 基于切量常用为
     * <p>
     * 1.后缀匹配，例 id：123456 匹配后缀为*456
     * 2.前缀匹配，例 id：123456 匹配前缀为123*
     *
     * @param args
     */
    public static void main(String args[]) {
        String content = "123456";

        String pattern = ".*123456.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 '123456' 子字符串? " + isMatch);

        pattern = "^123.*";

        isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是前缀是否为 '123' 子字符串? " + isMatch);

        pattern = ".*456$";

        isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是后缀是否为 '456' 子字符串? " + isMatch);
    }
}
