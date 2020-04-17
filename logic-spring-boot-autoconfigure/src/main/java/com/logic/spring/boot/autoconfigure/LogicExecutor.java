package com.logic.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.01:57
 */
public class LogicExecutor extends AbstractLogicExecutor {

    private Logger logger = LoggerFactory.getLogger(LogicExecutor.class);

    private LogicRepository logicRepository;

    public LogicExecutor(LogicRepository logicRepository) {
        this.logicRepository = logicRepository;
    }

    @Override
    protected <Func> Func getFunction(Class<Func> targetClz, Object condition) {
        logger.debug("getFunction: " + targetClz.getClass().getSimpleName());
        Func func = getFunctionBycondition(targetClz, condition);
        return func;
    }

    private <Func> Func getFunctionBycondition(Class<Func> targetClz, Object condition) {
        checkNull(condition);

        String logicFunctionName = targetClz.getName();
        logger.debug("getFunctionBycondition :logicFunctionName:{} ,LogicCondition:{}", logicFunctionName, condition);

        LogicCoordinate coordinate = new LogicCoordinate(logicFunctionName, condition);

        Func logicFunction = (Func) logicRepository.getFunction(coordinate);

        if (logicFunction != null) {
            return logicFunction;
        }

        throw new RuntimeException("Can not find function with LogicFunction:" + logicFunctionName + " LogicCondition:" + condition);

    }

    private void checkNull(Object condition) {
        if (condition == null) {
            throw new RuntimeException("condition can not be null for LogicFunction");
        }
    }
}
