package com.mypro.book.pojo;



import com.mypro.book.service.impl.BookServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Data
@Component
public class Cart {
    @Autowired
    BookServiceImpl bookService;

    private List<CartItem> cartItems;//购物车主
    private Double totalMoney;//购物车的总净额
    private Integer totalCount;  //购物项的数量
    private Integer totalBookCount;//多少种书

    public Integer getTotalBookCount() {
        totalBookCount=0;
        if(cartItems!=null&&cartItems.size()>0){
            for(CartItem cartItem:cartItems){
                totalBookCount = totalBookCount+cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }

    public Cart() {
    }



    public Double getTotalMoney() {
        totalMoney = 0.0;
//        DecimalFormat df3 = new DecimalFormat("###.000");
        for(CartItem cartItem:cartItems){

            Book book = bookService.getBook(cartItem.getBook().getId());
            totalMoney = totalMoney + book.getPrice() * cartItem.getBuyCount();

        }

        return (Double)(Math.round(totalMoney*100)/100.0);
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if(cartItems!=null&&cartItems.size()>0){
            totalCount = cartItems.size();
        }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }
}
