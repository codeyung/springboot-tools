package com.code.data;

import com.code.data.service.UserService;
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
    private UserService userService;


    @Test
    public void test(){
        List<User> list = userService.getAll();
        list.forEach(System.out::println);
    }



}
