package com.mypro.book.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
//订单详情页
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private String orderNo;//订单惟一主要标识
    private Date orderDate;
    private User orderUser;

    private Double orderMoney;
    private Integer orderStatus;

    private List<OrderItem> orderItemList;

    public Order(String orderNo, Date orderDate, User orderUser, Double orderMoney) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderUser = orderUser;
        this.orderMoney = orderMoney;
    }

    public Order(Integer id) {
        this.id = id;
    }




}
