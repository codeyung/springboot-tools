package com.code.datasource.config;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.18:35
 */
public interface RoutingStrategy {
    /**
     * 数据源路由策略
     *
     * @param source 源
     * @param ext    扩展
     * @return
     */
    void route(String source, Object ext) throws Exception;
}
