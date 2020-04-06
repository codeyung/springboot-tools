package com.code.datasource.config;


/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.18:35
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String type) {
        HOLDER.set(type);
    }

    public static String getDataSourceType() {
        return HOLDER.get();
    }

    public static void removeDataSourceType() {
        HOLDER.remove();
    }
}
