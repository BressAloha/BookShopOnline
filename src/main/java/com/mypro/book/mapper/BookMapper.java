package com.mypro.book.mapper;

import com.mypro.book.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> getBookList(Integer page);
    Book getBook(Integer id);
    Integer getBookCount();
}
