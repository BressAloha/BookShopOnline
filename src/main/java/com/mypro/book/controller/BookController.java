package com.mypro.book.controller;


import com.mypro.book.pojo.Cart;
import com.mypro.book.pojo.User;
import com.mypro.book.service.impl.BookServiceImpl;
import com.mypro.book.service.impl.CartItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
@Slf4j
@Controller
public class BookController {
    @Autowired
    CartItemServiceImpl cartItemService;
    @Autowired
    BookServiceImpl bookService;
    @GetMapping(value = {"/","/hello"})
    public String IndexPage(HttpSession session){

        return "index";
    }
    @GetMapping("/changePage")
    public String ChangePage(HttpSession session,@Param("pageNum") Integer pageNum ){

        Integer tempNum=(Integer)session.getAttribute("allPage");
        Integer page = 1;
        if(pageNum!=null){
            page = pageNum;
            if(page<=0){
                page=tempNum;
            }
            if(page>tempNum){
                page =1;
            }
        }
        session.setAttribute("pageNum",page);
        System.out.println(bookService.getBookList(page-1));
        session.setAttribute("bookList",bookService.getBookList(page-1));
        return "index";
    }
}
