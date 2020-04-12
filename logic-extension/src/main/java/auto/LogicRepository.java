package auto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 逻辑存储对象
 */
@Component
public class LogicRepository {

    private static final Logger logger = LoggerFactory.getLogger(LogicRepository.class);

    private Map<LogicCoordinate, LogicFunction> functionMap = new ConcurrentHashMap<>();

    public LogicFunction getFunction(LogicCoordinate coordinate) {
        if (coordinate == null) {
            return null;
        }
        logger.debug("[getFunction] {}", coordinate);
        return functionMap.get(coordinate);
    }


    public void putFunction(LogicCoordinate coordinate, LogicFunction function) throws RuntimeException {
        if (coordinate == null || function == null) {
            return;
        }
        LogicFunction logicFunction = functionMap.put(coordinate, function);
        if (logicFunction != null) {
            throw new RuntimeException("LogicRepository putFunction is not allowed for :" + coordinate);
        }
    }

}
