package com.example.sample.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sample.model.Book;
import com.example.sample.response.Response;
import com.example.sample.service.BookService;

@RestController
public class AdminController {
    
    @Autowired
    BookService bookService;
     
    //get all books
    @GetMapping("/books")
    public Response getBooks(){
        // List<Book>list= this.bookService.getAllBooks();
        // if(list.size()<=0){
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // }
        // else{
        //     return ResponseEntity.of(Optional.of(list));
        // }
        Response response=null;
            List<Book> list = bookService.getAllBooks();
            if(list.size()==0){
                response= new Response("Books not present in database", 101, list);
            }
            else{
             response = new Response("Fetched Books", 200, list);
            }
            return response;

        
    }

    //get book by id
    @GetMapping("/books/{id}")
    public Response getBook(@PathVariable("id") int id){
      
        // if(book==null){
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // }
        // return ResponseEntity.of(Optional.of(book));
       
            return this.bookService.getBookById(id);
       
    }

    //add book
    @PostMapping("/books")
    public ResponseEntity<Response> addBook(@RequestBody Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Response>(new Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),101,"Empty Fiels"),HttpStatus.BAD_REQUEST);
        }
        Book b=this.bookService.addBook(book);
        return new  ResponseEntity<Response>(new Response("Book Added Successfully",200,b),HttpStatus.OK);
    }

    //Delete book by id
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Response> deleteBook(@PathVariable("bookId") int bookId){
         this.bookService.deleteBook(bookId);
         return new  ResponseEntity<Response>(new Response("Book Deleted Successfully",200,null),HttpStatus.OK);
    }
    
    //Update book
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Response> updateBook(@RequestBody Book book , @PathVariable("bookId") int bookId){
      Book newBook= this.bookService.updateBook(book , bookId);
      return new  ResponseEntity<Response>(new Response("Book Added Successfully",200,newBook),HttpStatus.OK);
    }
}