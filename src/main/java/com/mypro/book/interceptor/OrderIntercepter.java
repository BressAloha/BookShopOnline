package com.mypro.book.interceptor;

import com.mypro.book.pojo.Order;
import com.mypro.book.pojo.User;
import com.mypro.book.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
@Component
public class OrderIntercepter implements HandlerInterceptor {
    @Autowired
    OrderServiceImpl orderService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null){
            session.setAttribute("msg","请先登陆");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("进入Order拦截器！");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<Order> orders = orderService.getOrder(user);
        session.setAttribute("orders",orders);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
