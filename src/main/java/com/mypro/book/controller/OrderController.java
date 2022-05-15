package com.mypro.book.controller;

import com.mypro.book.pojo.*;
//import com.mypro.book.service.OrderItemService;
import com.mypro.book.service.impl.CartItemServiceImpl;
//import com.mypro.book.service.impl.OrderItemServiceImpl;
import com.mypro.book.service.impl.OrderItemServiceImpl;
import com.mypro.book.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
//一下所有方法都将被OrderIntercepter拦截器方法拦截，并对其进行session注入
@Slf4j
@Controller
public class OrderController {
    @Autowired
    OrderItemServiceImpl orderItemService;
    @Autowired
    CartItemServiceImpl cartItemService;
    @Autowired
    OrderServiceImpl orderService;
    @GetMapping("/pay")
    public String PayOrder(HttpSession session){
        User user = (User)session.getAttribute("user");//获取用户
        //判断购物车是否有订单
        Cart cart = user.getCart();
        if(cart!=null&&cart.getCartItems().size()!=0){

            Date date = new Date();
            Long x = date.getTime();
            Order order = new Order(x.toString(),date,user,user.getCart().getTotalMoney());
//            List<Order>  orders= orderService.getOrder(user);
//            session.setAttribute("orders",orders);
            //删除购物车,同时生成详情订单
            //更新订单数据
            log.info("create an order!");
            orderService.addOrder(order);

            List<CartItem> cartItems = cart.getCartItems();
            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderBean(order);
                orderItem.setBuyCount(cartItem.getBuyCount());
                orderItem.setBook(cartItem.getBook());
//                orderItemService.addOrderItem(orderItem);
                cartItemService.delCartItem(cartItem);
            }
            //更新user对象里的数值
            cartItems.clear();
            cart.setCartItems(cartItems);
            user.setCart(cart);



            //跟新订单详情

            return "/pages/order/order";
        }
//        List<Order>  orders= orderService.getOrder(user);
//        session.setAttribute("orders",orders);
        return "/pages/order/order";

    }
    @GetMapping("/order")
    public String orderPage(){
        return "/pages/order/order";
    }
    @GetMapping("/delAllOrder")
    public String delOrder(HttpSession session){
        User user = (User)session.getAttribute("user");
        orderService.delOrder(user);
//        List<Order>  orders= orderService.getOrder(user);
//        session.setAttribute("orders",orders);
        return "/pages/order/order";
    }
    @GetMapping("/delOrder")
    public String delOrderByOrderBean(@Param("orderId")String orderId){

//        List<Order> orders = orderService.getOrder(user);

        orderService.delOrderById(orderId);
        return "/pages/order/order";
    }
    @GetMapping("/detailOrder")
    public String detailOrder(@Param("orderBean")String orderBean,HttpSession session)
    {
//        List<OrderItem> orderItems = orderItemService.getOrderItem(orderBean);
//        session.setAttribute("orderItem",orderItems);
        return "/order/order_manager";

    }

}
