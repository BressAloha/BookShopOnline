package com.mypro.book.service;

import com.mypro.book.pojo.Order;
import com.mypro.book.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    List<Order> getOrder(User user);
    void delOrder(User user);
    Integer delOrderById(String orderId);
    Order getOrderByOrderNo(String OrderNo);
}
