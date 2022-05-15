package com.mypro.book.interceptor;

import com.mypro.book.pojo.Book;
import com.mypro.book.pojo.Cart;
import com.mypro.book.pojo.CartItem;
import com.mypro.book.pojo.User;
import com.mypro.book.service.impl.BookServiceImpl;
import com.mypro.book.service.impl.CartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.zip.CheckedOutputStream;

@Component
public class CartIntercepter implements HandlerInterceptor {
    @Autowired
    CartItemServiceImpl cartItemService;
    @Autowired
    BookServiceImpl bookService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            session.setAttribute("msg","请先登陆！！！");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = cartItemService.getCart(user);
        cart.setTotalBookCount(cart.getCartItems().size());
        List<CartItem> cartItems=cart.getCartItems();


        //下面获取购物车的详细信息

        for (CartItem cartItem : cartItems) {
            Book book = bookService.getBook(cartItem.getBook().getId());
            cartItem.setBook(book);
            Double xj = (book.getPrice()*1000)*cartItem.getBuyCount()/1000;

            cartItem.setXj(xj);
            //更新进数据库
            cartItemService.updateCartItem(cartItem);
        }


        //注入session域中
        cart.setCartItems(cartItems);
        user.setCart(cart);
        session.setAttribute("user",user);

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
