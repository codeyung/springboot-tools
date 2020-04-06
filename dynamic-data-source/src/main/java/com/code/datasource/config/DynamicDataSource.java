package com.code.datasource.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.18:35
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String type = DynamicDataSourceContextHolder.getDataSourceType();
        if (StringUtils.isBlank(type)) {
            throw new IllegalStateException("Cannot set lookup key for datasource!!!");
        }
        return type;
    }
}
