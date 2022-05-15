package com.mypro.book.mapper;

import com.mypro.book.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    //添加订单项
    Integer addOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItem(String orderNo);//查询订单细节
    Integer delOrderItemByOrderNo(String orderNo);
}
