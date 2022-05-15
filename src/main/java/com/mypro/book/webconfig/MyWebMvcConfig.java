package com.mypro.book.webconfig;

import com.mypro.book.interceptor.CartIntercepter;
import com.mypro.book.interceptor.OrderIntercepter;
import com.mypro.book.interceptor.UserIntercepter;
import com.mypro.book.servlet.MyHttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    UserIntercepter userIntercepter;
    @Autowired
    CartIntercepter cartIntercepter;
    @Autowired
    MyHttpSessionListener myHttpSessionListener;
    @Autowired
    OrderIntercepter orderIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIntercepter).addPathPatterns("**").excludePathPatterns("/","/login","/regist","/css/**","/img/**","/script/**","/uploads/**","/login_try","/regist_try");
        registry.addInterceptor(cartIntercepter).addPathPatterns("/cart","/editCart","/addCart","/delCartItem");
        registry.addInterceptor(orderIntercepter).addPathPatterns("/order","/pay","/delAllOrder","/delOrder");


        WebMvcConfigurer.super.addInterceptors(registry);
    }
//    @ConfigurationProperties("spring.datasource")
//    @Bean
//    public DataSource dataSource() throws SQLException {
//        return new DruidDataSource();
//}
    @Bean
    public ServletListenerRegistrationBean registrationBean(){
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
        registrationBean.setListener(myHttpSessionListener);
        return registrationBean;
    }
}
