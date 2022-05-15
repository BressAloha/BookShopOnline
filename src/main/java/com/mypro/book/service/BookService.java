package com.mypro.book.service;

import com.mypro.book.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList(Integer page);
    Integer getBookCount();
    Book getBook(Integer id);
}
