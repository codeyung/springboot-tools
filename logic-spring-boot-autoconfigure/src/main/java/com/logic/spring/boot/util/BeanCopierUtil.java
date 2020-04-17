package com.logic.spring.boot.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: YongJingChuan
 * @Date: 2019/7/3 16:02
 */
public class BeanCopierUtil {

    private static ConcurrentHashMap<String, BeanCopier> copierMap = new ConcurrentHashMap<>(8);
    private static final String FLAG = "-";

    public static void copy(Object source, Object target) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }

    private static BeanCopier getBeanCopier(Class sourceClass, Class targetClass) {
        String key = getKey(sourceClass, targetClass);
        BeanCopier copier;
        if (!copierMap.containsKey(key)) {
            copier = BeanCopier.create(sourceClass, targetClass, false);
            BeanCopier _copier = copierMap.putIfAbsent(key, copier);
            if (_copier != null) {
                copier = _copier;
            }
        } else {
            copier = copierMap.get(key);
        }
        return copier;
    }

    public static <T1, T2> List<T2> copy(Collection<T1> sourceCollection, Class<T2> targetClass) {
        List<T2> toList = new ArrayList<>();
        for (T1 source : sourceCollection) {
            try {
                T2 target = targetClass.newInstance();
                copy(source, target);
                toList.add(target);
            } catch (Exception e) {
                System.out.println("Bean copy error occurs when from " + sourceCollection.getClass().getName() + " to " + targetClass.getClass().getName());
                return null;
            }
        }
        return toList;
    }

    private static String getKey(Class source, Class target) {
        String sourceName = source.getSimpleName();
        String targetName = target.getSimpleName();
        return new StringBuilder(sourceName).append(FLAG).append(targetName).toString();
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

}
