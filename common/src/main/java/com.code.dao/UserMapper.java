package com.code.dao;

import com.code.entity.User;

import java.util.List;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-06.17:14
 */
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<User> getAll();
}
