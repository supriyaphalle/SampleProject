package com.example.sample.service;

import java.util.List;

import com.example.sample.model.Book;
import com.example.sample.response.Response;

public interface IBookService {
    
    public List<Book> getAllBooks();

    public Response getBookById(int id);

    public Book addBook(Book b);

    public void deleteBook(int id);

    public  Book updateBook(Book book,int id);
}
