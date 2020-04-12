package auto;

import function.CheckedFunction;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-10.17:05
 */
public abstract class AbstractLogicExecutor {


    public <R, T> R execute(Class<T> targetClz, LogicCondition condition, Function<T, R> exeFunction) {
        T logic = getFunction(targetClz, condition);
        return exeFunction.apply(logic);
    }

    public <R, T, E extends Exception> R executeCheckedFunc(Class<T> targetClz, LogicCondition condition, CheckedFunction<T, R, E> exeFunction) throws E {
        T logic = getFunction(targetClz, condition);
        return exeFunction.apply(logic);
    }

    public <T> void executeVoid(Class<T> targetClz, LogicCondition condition, Consumer<T> exeFunction) {
        T logic = getFunction(targetClz, condition);
        exeFunction.accept(logic);
    }

    public <T, E extends Exception> void executeVoidCheckedFunc(Class<T> targetClz, LogicCondition condition, CheckedConsumer<T, E> exeFunction) throws E {
        T logic = getFunction(targetClz, condition);
        exeFunction.accept(logic);
    }

    protected abstract <Func> Func getFunction(Class<Func> targetClz, LogicCondition condition);
}
