package com.code.datasource.service;

import com.code.dao.UserMapper;
import com.code.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.18:35
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public List<User> getAll(){
        return userMapper.getAll();
    }

}
