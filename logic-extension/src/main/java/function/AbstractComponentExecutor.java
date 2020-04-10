package function;

import java.util.function.Function;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-10.17:05
 */
public class AbstractComponentExecutor {


    public <R, T> R execute(Class<T> targetClz, String gender, Function<T, R> exeFunction) {
        T component = locateComponent(targetClz, gender);
        return exeFunction.apply(component);
    }

    public <R, T, E extends Exception> R executeCheckedFunc(Class<T> targetClz, String gender, CheckedFunction<T, R, E> exeFunction) throws E {
        T component = locateComponent(targetClz, gender);
        return exeFunction.apply(component);
    }

    protected <C> C locateComponent(Class<C> targetClz, String gender) {
        C func = locateFunc(targetClz, gender);
        return func;
    }

    protected <Ext> Ext locateFunc(Class<Ext> targetClz, String gender) {

//        String funcName = targetClz.getName();
//
//        Ext func;
//
//        if (funcName.contains(gender)) {
//            func = (Ext) new Female();
//            return func;
//        }
//
//        if (funcName.contains(gender)) {
//            func = (Ext) new Male();
//            return func;
//        }

        Ext func;

        if (gender.contains("Female")) {
            func = (Ext) new Female();
            return func;
        }

        if (gender.contains("Male")) {
            func = (Ext) new Male();
            return func;
        }

        if (gender.contains("FException")) {
            func = (Ext) new FemaleException();
            return func;
        }

        if (gender.contains("MException")) {
            func = (Ext) new MaleException();
            return func;
        }

        throw new RuntimeException("func not found");

    }
}
