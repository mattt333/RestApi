package com.quete.restapi.controller;

import java.util.List;
import java.util.Map;

import com.quete.restapi.entity.Book;
import com.quete.restapi.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping ("/books")
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping ("/books/{id}")
    public Book show(@PathVariable int id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }
    
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
       return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }


    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }

    
}