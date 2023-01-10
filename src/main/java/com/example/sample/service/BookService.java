package com.example.sample.service;
import com.example.sample.exception.BookException;
import com.example.sample.model.Book;
import com.example.sample.repository.BookRepository;
import com.example.sample.response.Response;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    //get all book
    @Override
    public List<Book> getAllBooks(){
        
        List <Book> list= (List<Book>)this.bookRepository.findAll();
        return list;

    }

    //get single book by id
    @Override
    public Response getBookById(int id){
        Book book=this.bookRepository.findById(id);
       if(book!= null){
            return new Response("Book by Id", 200, book);
        }
        
        return new Response ("Book not Found", 101,BookException.ExceptionType.BOOK_DOES_NOT_EXIST);
        
    }

    //adding the book
    @Override
    public Book addBook(Book b){
       
        Book result=this.bookRepository.save(b);
        return result;
    }

    //Delete book
   @Override
    public void deleteBook(int id){
    
        bookRepository.deleteById(id);
    
    }

    //Update Book
    @Override
    public  Book updateBook(Book book,int id){
    
        book.setId(id);
        bookRepository.save(book);
        return this.bookRepository.findById(id);       
    }
}