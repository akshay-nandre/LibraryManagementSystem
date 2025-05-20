package com.book.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.library.model.Book;
import com.book.library.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {
		return bookService.getBookByID(id);
	}

	@PostMapping
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book){
		Book saveBook=bookService.createBook(book);
		return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public Book updateBook( @PathVariable Long id, @Valid @RequestBody Book book){
		return bookService.updateBook(id, book);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id){
		bookService.deleteBookById(id);
		return ResponseEntity.noContent().build();
	}
}
