package starter.impl;

import org.springframework.stereotype.Service;
import starter.annotation.Logic;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@Logic
public class TestReturnReturnDefault implements TestReturnFunction {

    @Override
    public int puls(int var) {
        System.out.println("TestReturnReturnDefault");
        return var;
    }
}
