package com.mypro.book.mapper;

import com.mypro.book.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUser(String username, String password);
    Integer addUser(User user);
    Integer delUser(User user);
    Integer getcountUser();
    User getUserByName(String username);
}
