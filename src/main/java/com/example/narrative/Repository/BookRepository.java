package com.example.narrative.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.narrative.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
