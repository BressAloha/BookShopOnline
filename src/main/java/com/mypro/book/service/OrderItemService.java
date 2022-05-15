package com.mypro.book.service;

import com.mypro.book.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    Integer addOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItem(String orderNo);
    Integer delOrderItemByOrderNo(String orderNo);
}
