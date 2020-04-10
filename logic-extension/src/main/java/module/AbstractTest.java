package module;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-10.16:39
 */
public abstract class AbstractTest<R, P> {

    public final R test(P var1) {
        this.valid(var1);
        return this.process(var1);
    }

    protected abstract R process(P var1);

    protected abstract void valid(P var1);


}
