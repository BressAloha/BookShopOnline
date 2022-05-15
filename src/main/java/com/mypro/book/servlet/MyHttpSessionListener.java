package com.mypro.book.servlet;

import com.mypro.book.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@Component
public class MyHttpSessionListener implements HttpSessionListener {
    @Autowired
    BookServiceImpl bookService;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
//        System.out.println("监听器只生效一次目的是为了生成与user无关的数据");
        if(session.getAttribute("pageSum")==null){
            session.setAttribute("pageSum",bookService.getBookCount());
        }
        if(session.getAttribute("allPage")==null){
            session.setAttribute("allPage",(bookService.getBookCount()+4)/5);
        }
        if(session.getAttribute("pageNum")==null){
            session.setAttribute("pageNum",1);
        }
        if(session.getAttribute("bookList")==null){
            session.setAttribute("bookList",bookService.getBookList(0));
        }
        if(session.getAttribute("msg")==null){
            session.setAttribute("msg","请输入用户名和密码");
        }
        if(session.getAttribute("registInfo")==null){
            session.setAttribute("registInfo","请注册");
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
