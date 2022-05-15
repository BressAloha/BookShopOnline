package com.mypro.book.pojo;

import com.mypro.book.service.impl.BookServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Integer id;
    private Book book;//书名id
    private Integer buyCount;
    private User userBean;//所属用户
    private Double xj;//此订单金额

//    @Autowired
//    BookServiceImpl bookService;
//    public double getXj() {
//        float tempxj = (float) 1.0;
//        tempxj =(float) (book.getPrice()*buyCount);
//        return  (double) Math.round((float)(tempxj*100))/100.0;
//    }

//    public Book getBook() {
//        return bookService.getBook(book.getId());
//    }

//    public Double getXj() {
//        double tempxj = (float) 1.0;
////        tempxj =(float) (book.getPrice()*buyCount);
//        return  Math.round((float)(tempxj*100))/100.0;
//    }


//    public double getXj() {
//        double tempxj = (float) 1.0;
//        tempxj =(float) (book.getPrice()*buyCount);
//        return (double) Math.round((float)(tempxj*100))/100.0;
//    }

    public CartItem(Integer id){
        this.id = id;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }
}
