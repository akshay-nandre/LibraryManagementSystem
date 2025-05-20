package com.book.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.library.model.Book;


public interface BookRepository  extends JpaRepository<Book, Long>{

}
