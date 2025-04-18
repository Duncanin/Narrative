package com.example.narrative.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.narrative.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    
}
