package com.mypro.book.service;

import com.mypro.book.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User login(String username, String password);
    Integer addUser(User user);
    Integer delUser(User user);
    User getUserByName(String username);
}
