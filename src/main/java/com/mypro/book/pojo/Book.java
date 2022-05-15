package com.mypro.book.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
        private Integer id;
        private String bookImg;
        private String bookName;
        private Double price;
        private String author;
        private Integer saleCount;
        private Integer bookCount;
        private Integer bookStatus;
        public Book(Integer id) {
                this.id = id;
        }
}
