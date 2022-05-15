package com.mypro.book.pojo;

import lombok.Data;

@Data
public class OrderItem {
    private Integer id;
    private Book book;//书名id
    private Integer buyCount;
    private Order orderBean;//所属订单

    public OrderItem(Integer id) {
        this.id = id;
    }

    public OrderItem() {
    }


}
