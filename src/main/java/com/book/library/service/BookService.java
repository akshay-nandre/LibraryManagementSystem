package com.book.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.book.library.exception.ResourceNotFoundException;
import com.book.library.model.Book;
import com.book.library.repository.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	} 

	public Book getBookByID(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found :" +id ));
	}

	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	public Book updateBook(Long id, Book updateBook) {
		Book book = getBookByID(id);
		book.setTitle(updateBook.getTitle());
		book.setAuthor(updateBook.getAuthor());
		book.setPublicationYear(updateBook.getPublicationYear());
		return bookRepository.save(book);

	}

	public void deleteBookById(Long id) {
		Book book = getBookByID(id);
		bookRepository.delete(book);
	}
}
