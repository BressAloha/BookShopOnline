package com.mypro.book;

import com.mypro.book.mapper.*;
import com.mypro.book.pojo.*;
import com.mypro.book.service.CartItemService;
import com.mypro.book.service.impl.CartItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class BookShopOnlineApplicationTests {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    CartItemServiceImpl cartItemService;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Test
    void contextLoads() {
        System.out.println(bookMapper.getBookCount());



    }
    @Test
    void test1(){
//        System.out.println(userMapper.getUser("uname1","ok"));
//        System.out.println(userMapper.getcountUser());
        User user = new User();
        user.setPassword("1008611");
        user.setUsername("易楷");
        user.setEmail("921872135@qq.com");
        user.setRole(1);
//        userMapper.addUser(user);
        userMapper.delUser(user);
        System.out.println(userMapper.getUser(user.getUsername(),user.getPassword()));

    }
    @Test
    @Transactional
    void test2(){
        User user = userMapper.getUserByName("uname1");
        System.out.println(user);
        CartItem cart = new CartItem();
        cart.setBook(new Book(60));
        cart.setUserBean(new User(1));
        cart.setBuyCount(10);
        System.out.println(cart);
//        cartItemMapper.addCartItem(cart);
        cartItemMapper.delCartItem(cart);



        System.out.println(cartItemMapper.getCartItemList(user));
    }
    @Test
    void test3(){
        System.out.println("测试开始");
        Cart cart = cartItemService.getCart(new User(1));
        System.out.println(cart);
        System.out.println( cart.getTotalCount());
        System.out.println(cart.getCartItems());
        System.out.println(cart.getTotalMoney());

    }
    @Test
    void test4(){
        Order order = orderMapper.getOrderByOrderId("1652589954336");
        System.out.println(order);
    }
    @Test
    void test5(){
        System.out.println("uyys");
//        orderItemMapper.getOrderItem();
        OrderItem orderItem = new OrderItem();
        orderItem.setBook(new Book(43));
        Order order = orderMapper.getOrderByOrderId("1652589954336");
        System.out.println(order);
        orderItem.setOrderBean(order);
        orderItem.setBuyCount(3);
        System.out.println(orderItem);
        orderItemMapper.addOrderItem(orderItem);
        orderItemMapper.delOrderItemByOrderNo(order.getOrderNo());
    }
    @Test
    void test6(){
        List<OrderItem> orderItems=orderItemMapper.getOrderItem("1652589954336");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }



}
