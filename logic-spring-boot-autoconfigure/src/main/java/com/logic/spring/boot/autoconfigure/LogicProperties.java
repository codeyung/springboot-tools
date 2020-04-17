package com.logic.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.21:04
 */

@ConfigurationProperties(prefix = "logic")
public class LogicProperties {

    private LinkedHashMap<String, String> extensions;

    public LinkedHashMap<String, String> getExtensions() {
        return extensions;
    }

    public void setExtensions(LinkedHashMap<String, String> extensions) {
        this.extensions = extensions;
    }

    public boolean filterAnnotationClassName(String annotationClassName) {
        return extensions.containsKey(annotationClassName);
    }

    public String getConditionClassName(String annotationClassName) {
        return extensions.get(annotationClassName);
    }
}
