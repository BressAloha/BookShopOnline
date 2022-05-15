package com.mypro.book.controller;

import com.mypro.book.pojo.Book;
import com.mypro.book.pojo.Cart;
import com.mypro.book.pojo.CartItem;
import com.mypro.book.pojo.User;
import com.mypro.book.service.impl.BookServiceImpl;
import com.mypro.book.service.impl.CartItemServiceImpl;
import com.mypro.book.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class CartController {
    @Autowired
    CartItemServiceImpl cartItemService;
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    UserServiceImpl userService;
//下面所有的方法都会被拦截器拦截，进行用户判断并且注入用户以及用户购物车信息
    @GetMapping("/cart")
    public String toCart(){


        return "/pages/cart/cart";
    }
///editCart?cartItemId=11&buyCount=2
    @GetMapping("/editCart")
    public String editCart(
                           @Param("cartItemId")Integer cartItemId,
                           @Param("buyCount")Integer buyCount
                            ){
        CartItem cartItem = new CartItem(cartItemId);
        cartItem.setId(cartItemId);
        cartItem.setBuyCount(buyCount);
        cartItemService.updateCartItem(cartItem);
        return "/pages/cart/cart";
    }
    @GetMapping("/addCart")
    public String addCart(HttpSession session,
                          @Param("bookId")Integer bookId){
        User user = (User)session.getAttribute("user");


        List<CartItem> cartItems =cartItemService.getCart(user).getCartItems();

        for (CartItem cartItem : cartItems) {
            if(cartItem.getBook().getId()==bookId){

                return "index";
            }
        }
        cartItemService.addCartItem(new CartItem(new Book(bookId),1,user));


        return "index";
    }
    //delCartItem?cartItemid={cartItemid}(cartItemid=${cartItem.id})
    @GetMapping("/delCartItem")
    public String delCartItem(@Param("cartItemId")Integer cartItemId){
        CartItem cartItem = new CartItem(cartItemId);
        cartItem.setBuyCount(0);

        cartItemService.delCartItem(cartItem);

        return "/pages/cart/cart";
    }

}
