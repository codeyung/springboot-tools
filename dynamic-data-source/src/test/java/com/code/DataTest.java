package com.code;

import com.code.datasource.config.DefaultRouteStrategy;
import com.code.datasource.service.UserService;
import com.code.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.18:32
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataTest {

    @Autowired
    private DefaultRouteStrategy routeStrategy;

    @Autowired
    private UserService userService;


    private String source1 = "data1";
    private String source2 = "data2";
    private String ext = "";

    @Test
    public void test() {
        routeStrategy.route(source1, "");
        List<User> list = userService.getAll();
        list.forEach(System.out::println);
        routeStrategy.route(source2, "");
        list = userService.getAll();
        list.forEach(System.out::println);
    }


}
