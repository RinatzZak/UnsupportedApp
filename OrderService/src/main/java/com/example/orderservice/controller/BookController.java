package com.example.orderservice.controller;

import com.example.orderservice.entity.Book;
import com.example.orderservice.repository.impl.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.orderservice.Tables.*;

import java.io.NotActiveException;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository repository;


    @GetMapping("books/get-book")
    public Book getBook(@RequestParam int id) {
       return repository.find(id);
    }

    @GetMapping("books/all")
    public List<Book> getAll() {
        return repository.findAll();
    }
}
