package com.endriu.tohosted.controller;

import com.endriu.tohosted.entity.Book;
import com.endriu.tohosted.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookservice")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.getAllBooks().add(book);
    }

}
