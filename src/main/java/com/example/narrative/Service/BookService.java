// File: BookService.java
package com.example.narrative.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.narrative.entity.Book;
import com.example.narrative.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository repository;


    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}