package com.mypro.book.service.impl;

import com.mypro.book.mapper.BookMapper;
import com.mypro.book.pojo.Book;
import com.mypro.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> getBookList(Integer page) {
        Integer pagenum = page *5;
        return bookMapper.getBookList(pagenum);
    }

    @Override
    public Integer getBookCount() {
       return bookMapper.getBookCount();
    }

    @Override
    public Book getBook(Integer id) {
        return bookMapper.getBook(id);
    }
}
