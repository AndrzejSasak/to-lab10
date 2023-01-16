package com.endriu.tohosted.service;

import com.endriu.tohosted.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    private List<Book> books;

    public List<Book> getAllBooks() {
        return books;
    }

}
