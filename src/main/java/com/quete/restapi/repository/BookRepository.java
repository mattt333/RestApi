package com.quete.restapi.repository;

import java.util.List;

import com.quete.restapi.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {

   public List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
}
    
