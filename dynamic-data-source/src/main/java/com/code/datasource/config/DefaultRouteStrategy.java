package com.code.datasource.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.18:35
 */
@Component
public class DefaultRouteStrategy implements RoutingStrategy {

    private static Logger logger = LoggerFactory.getLogger(DefaultRouteStrategy.class);

    private Properties dataSourceRoutingConfig;
    private String dataSourceRoutingConfigFileName = "datasource_routing.properties";

    @PostConstruct
    public void loadRouteConfig() throws Exception {
        ClassPathResource cpr = new ClassPathResource(dataSourceRoutingConfigFileName);
        dataSourceRoutingConfig = new Properties();
        dataSourceRoutingConfig.load(cpr.getInputStream());
        logger.info("dataSourceRoutingConfig content:[{}]", dataSourceRoutingConfig.toString());
    }

    @Override
    public void route(String source, Object ext) throws RuntimeException {
        String dataSourceType = dataSourceRoutingConfig.getProperty(source);
        logger.info("route-source:[{}],  dataSourceType:[{}]", source, dataSourceType);

        if (StringUtils.isNotBlank(dataSourceType)) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceType);
            return;
        }

        throw new RuntimeException("dataSourceType not found!!!, source:[" + source + "]");
    }

}
