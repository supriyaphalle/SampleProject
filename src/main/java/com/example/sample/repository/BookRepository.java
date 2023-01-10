package com.example.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.Book;

public interface BookRepository extends CrudRepository<Book,Integer> {
    public Book findById(int id);
}
