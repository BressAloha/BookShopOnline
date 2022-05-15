package com.mypro.book.service.impl;

import com.mypro.book.mapper.UserMapper;
import com.mypro.book.pojo.User;
import com.mypro.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(String username, String password) {

        return userMapper.getUser(username,password);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Integer delUser(User user) {
        return userMapper.delUser(user);
    }

    @Override
    public User getUserByName(String username) {

        return userMapper.getUserByName(username);
    }
}
