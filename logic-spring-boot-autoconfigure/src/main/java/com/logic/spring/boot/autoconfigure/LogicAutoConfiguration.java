package com.logic.spring.boot.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-17.02:15
 */
@Configuration
@EnableConfigurationProperties(LogicProperties.class)
public class LogicAutoConfiguration {

    @Autowired
    private LogicProperties logicProperties;


    @Bean("logicRepository")
    @ConditionalOnMissingBean(LogicRepository.class)
    public LogicRepository logicRepository() {
        return new LogicRepository();
    }

    @Bean
    @ConditionalOnMissingBean(LogicExecutor.class)
    public LogicExecutor logicExecutor(@Qualifier("logicRepository") LogicRepository logicRepository) {
        return new LogicExecutor(logicRepository);
    }


    @Bean
    @ConditionalOnMissingBean(LogicBeanPostProcessor.class)
    public LogicBeanPostProcessor logicBeanPostProcessor(@Qualifier("logicRepository") LogicRepository logicRepository) {
        return new LogicBeanPostProcessor(logicProperties, logicRepository);
    }


}
