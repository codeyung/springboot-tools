package auto.impl;

import auto.annotation.Logic;
import org.springframework.stereotype.Service;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.02:16
 */
@Service
@Logic(group = "one", type = 1)
public class TestVoidOne implements TestVoidFunction {
    @Override
    public void puls(int var) {
        System.out.println("TestVoidOne");
    }
}
