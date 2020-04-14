package auto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.01:57
 */
@Component
public class LogicExecutor extends AbstractLogicExecutor {

    private Logger logger = LoggerFactory.getLogger(LogicExecutor.class);

    @Autowired
    private LogicRepository logicRepository;

    @Override
    protected <Func> Func getFunction(Class<Func> targetClz, LogicCondition condition) {
        logger.debug("getFunction : " + targetClz.getClass().getSimpleName());
        Func func = getFunctionBycondition(targetClz, condition);
        return func;
    }

    private <Func> Func getFunctionBycondition(Class<Func> targetClz, LogicCondition condition) {
        checkNull(condition);

        String logicFunctionName = targetClz.getName();
        logger.debug(" getFunctionBycondition :logicFunctionName:{} ,LogicCondition:{}", logicFunctionName, condition);

        LogicCoordinate coordinate = new LogicCoordinate(logicFunctionName, condition);

        Func logicFunction = (Func) logicRepository.getFunction(coordinate);

        if (logicFunction != null) {
            return logicFunction;
        }

        throw new RuntimeException("Can not find function with LogicFunction:" + logicFunctionName + " LogicCondition:" + condition);

    }

    private void checkNull(LogicCondition condition) {
        if (condition == null) {
            throw new RuntimeException("condition can not be null for LogicFunction");
        }
    }
}
