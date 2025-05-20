package com.book.library.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.book.library.exception.ResourceNotFoundException;
import com.book.library.model.Book;
import com.book.library.repository.BookRepository;

@SpringBootTest
public class BookServiceTest {

	@MockBean
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void testCreateBook() {
		Book book = new Book();
		book.setTitle("Core Java 11 Core Java 11 Fundamentals ");
		book.setAuthor("Cay S. Horstmann");
		book.setPublicationYear(1996);
		
		when(bookRepository.save(ArgumentMatchers.any(Book.class))).thenReturn(book);
		
		Book saved = bookService.createBook(book);
		assertEquals("Core Java 11 Core Java 11 Fundamentals ", saved.getTitle());
	}

	@Test
	public void testGetAllBooks() {
		Book book = new Book();
		book.setTitle("Advanced Java");
		when(bookRepository.findAll()).thenReturn(List.of(book));
		List<Book> books = bookService.getAllBooks();
		assertEquals(1, books.size());
	}
	
	@Test
	public void testGetBookIdSuccess() {
		Book book = new Book();
		book.setId(1L);
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		Book result=bookService.getBookByID(1L);
		assertEquals(1L, result.getId());
	}
	
	@Test
	public void testGetBookById() {
		when(bookRepository.findById(2L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> bookService.getBookByID(2L));
	}
	
	@Test
	public void testUpdateBook() {
		Book book = new Book();
		book.setId(1L);
		book.setTitle(".Net");
		
		Book updateBook= new Book();
		updateBook.setTitle("Python");
		updateBook.setAuthor("Guido van Rossum");
		updateBook.setPublicationYear(1991);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		when(bookRepository.save(any(Book.class))).thenReturn(updateBook);
		
		Book result= bookService.updateBook(1L, updateBook);
		assertEquals("Python", result.getTitle());
	}
	
	@Test
	public void testDeleteBook() {
		Book book = new Book();
		book.setId(1L);
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		doNothing().when(bookRepository).delete(book);
		assertDoesNotThrow(() -> bookService.deleteBookById(1L));
	}
	
}
