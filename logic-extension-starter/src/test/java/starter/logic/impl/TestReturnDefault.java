package starter.logic.impl;

import org.springframework.stereotype.Service;
import starter.annotation.Logic;
import starter.logic.TestReturnFunction;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@Logic
public class TestReturnDefault implements TestReturnFunction {

    @Override
    public int puls(int var) {
        System.out.println("TestReturnReturnDefault");
        return var;
    }
}
