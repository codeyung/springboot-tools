package auto.annotation;

import auto.LogicCondition;
import auto.LogicCoordinate;
import auto.LogicFunction;
import auto.LogicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.01:22
 */
@Component
public class LogicBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(LogicBeanPostProcessor.class);

    @Autowired
    private LogicRepository logicRepository;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessAfterInitialization-start, beanName:{}", beanName);
        Class<?> targetClass = bean.getClass();

        if (bean instanceof LogicFunction) {
            if (!targetClass.isAnnotationPresent(Logic.class)) {
                throw new RuntimeException("Interface LogicFunction not found Logic annotation, beanName:" + beanName);
            }

            Logic logic = targetClass.getAnnotation(Logic.class);

            LogicCondition condition = new LogicCondition(logic.group(), logic.type());

            LogicCoordinate coordinate = new LogicCoordinate(targetClass, condition);

            registerFunction(bean, beanName, coordinate);
        }

        return bean;
    }

    private void registerFunction(Object bean, String beanName, LogicCoordinate coordinate) {
        logger.info("registerFunction-beanName:{}, coordinate:{}", beanName, coordinate);
        logicRepository.putFunction(coordinate, (LogicFunction) bean);
    }

}
