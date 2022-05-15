package com.mypro.book.service.impl;

import com.mypro.book.mapper.OrderMapper;
import com.mypro.book.pojo.Order;
import com.mypro.book.pojo.User;
import com.mypro.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    @Override
    public List<Order> getOrder(User user) {
        return  orderMapper.getOrder(user);
    }

    @Override
    public void delOrder(User user) {
        orderMapper.delOrder(user);
    }

    @Override
    public Integer delOrderById(String orderId) {
        return orderMapper.delOrderByOrderId(orderId);
    }

    @Override
    public Order getOrderByOrderNo(String OrderNo) {
        return null;
    }
}
