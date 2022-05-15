package com.mypro.book.mapper;

import com.mypro.book.pojo.Order;
import com.mypro.book.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer addOrder(Order order);
    List<Order> getOrder(User user);
    void delOrder(User user);//删除全部订单的
    Integer delOrderByOrderId(String orderBean);
    Order getOrderByOrderId(String orderBean);
}
