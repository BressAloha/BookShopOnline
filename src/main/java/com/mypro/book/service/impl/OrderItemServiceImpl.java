package com.mypro.book.service.impl;

import com.mypro.book.mapper.OrderItemMapper;
import com.mypro.book.pojo.OrderItem;
import com.mypro.book.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Override
    public Integer addOrderItem(OrderItem orderItem) {
        return orderItemMapper.addOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItem(String orderNo) {
        return orderItemMapper.getOrderItem(orderNo);
    }

    @Override
    public Integer delOrderItemByOrderNo(String orderNo) {
        return orderItemMapper.delOrderItemByOrderNo(orderNo);
    }
}
