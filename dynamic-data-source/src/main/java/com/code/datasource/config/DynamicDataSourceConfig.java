package com.code.datasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.20:47
 */
@Configuration
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.code.dao")
public class DynamicDataSourceConfig {

    @Value("${mybatis.mapperLocations}")
    private String configLocation;
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasespackage;


    @Bean("data1")
    @ConfigurationProperties("spring.datasource.hikari.data1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("data2")
    @ConfigurationProperties("spring.datasource.hikari.data2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("data1") DataSource data1, @Qualifier("data2") DataSource data2) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put("data1", data1);
        targetDataSources.put("data2", data2);
        return new DynamicDataSource(targetDataSources);
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(configLocation));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasespackage);
        return sqlSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
