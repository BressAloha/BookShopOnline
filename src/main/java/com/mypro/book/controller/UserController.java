package com.mypro.book.controller;

import com.mypro.book.mapper.UserMapper;
import com.mypro.book.pojo.User;
import com.mypro.book.service.UserService;
import com.mypro.book.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @RequestMapping("/login")
    public String Login(HttpSession session)
    {
        if(session.getAttribute("user")!=null){
            return "index";
        }else
        {
            return "pages/user/login";
        }
    }
    @PostMapping("/login_try")
    public String loginTry(HttpSession session,
                           @Param("username") String username,
                           @Param("password") String password
                           ) {
        System.out.println("进入login_try");
        System.out.println(username+password);
        User user = userService.login(username,password);
        if(user==null){
            session.setAttribute("msg","请输入正确的账号或者密码");
//            request.getRequestDispatcher("/login").forward(request,response);
            return "pages/user/login";
        }
        session.setAttribute("user",user);
        return "index";
    }
    //注销功能
    @GetMapping("/logout")
    public String Logout(HttpSession session){
        session.setAttribute("user",null);
        return "index";
    }

    @GetMapping("/regist")
    public String Regist(){
        return "pages/user/regist";
    }
    @PostMapping("/regist_try")
    public String RegistTry(HttpSession session,
                            @Param("username")String username,
                            @Param("password")String password,
                            @Param("comfirm_password") String comfirm_password,
                            @Param("email") String email) throws ServletException, IOException {
        System.out.println(userService.getUserByName(username));
        if(!StringUtils.equals(password,comfirm_password)){
            System.out.println(password);
            System.out.println(comfirm_password);
            session.setAttribute("registInfo","两次密码不一致！！");
            return "pages/user/regist";
        }
        if(StringUtils.length(password)<2){
            session.setAttribute("registInfo","密码长度不够！！");
            return "pages/user/regist";
        }
        if(userService.getUserByName(username)!=null){
            System.out.println(userService.getUserByName(username));
            session.setAttribute("registInfo","该用户名已被注册");
            return "pages/user/regist";
        }

        User user = new User(username,password,email);
        userService.addUser(user);
        session.setAttribute("registInfo","");
        session.setAttribute("user",userService.getUserByName(user.getUsername()));//注册成功直接跳转
//        request.getRequestDispatcher("/").forward(request,response);
        return "index";
    }







}
