package starter.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import starter.config.LogicProperties;
import starter.function.LogicFunction;
import starter.util.BeanCopierUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.01:22
 */
@Component
public class LogicBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(LogicBeanPostProcessor.class);

    @Autowired
    private LogicProperties logicProperties;

    @Autowired
    private LogicRepository logicRepository;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("LogicBeanPostProcessor postProcessAfterInitialization-start, beanName:{}", beanName);
        Class<?> targetClass = bean.getClass();

        if (bean instanceof LogicFunction) {

            Annotation[] annotations = targetClass.getAnnotations();
            for (Annotation annotation : annotations) {

                String annotationClassName = annotation.annotationType().getName();

                if (logicProperties.filterAnnotationClassName(annotationClassName)) {

                    Map map = getMemberValues(annotation);

                    String conditionClassName = logicProperties.getConditionClassName(annotationClassName);

                    Object condition = createCondition(conditionClassName, map);
                    LogicCoordinate coordinate = new LogicCoordinate(targetClass, condition);

                    registerFunction(bean, beanName, coordinate);

                    return bean;
                }

            }

            throw new RuntimeException("LogicBeanPostProcessor Interface LogicFunction not found annotation, beanName:" + beanName);
        }

        return bean;

    }

    private Map getMemberValues(Annotation annotation) {
        try {
            InvocationHandler invo = Proxy.getInvocationHandler(annotation);
            Field field = invo.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            return (Map) field.get(invo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("LogicBeanPostProcessor Interface LogicFunction annotation not found values");
    }

    private Object createCondition(String conditionClassName, Map map) {
        try {
            Object condition = Class.forName(conditionClassName)
                    .newInstance();
            BeanCopierUtil.mapToBean(map, condition);
            return condition;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("LogicBeanPostProcessor Interface LogicFunction annotation createCondition fail");
    }


    private void registerFunction(Object bean, String beanName, LogicCoordinate coordinate) {
        logger.info("LogicBeanPostProcessor registerFunction-beanName:{}, coordinate:{}", beanName, coordinate);
        logicRepository.putFunction(coordinate, (LogicFunction) bean);
    }

}
