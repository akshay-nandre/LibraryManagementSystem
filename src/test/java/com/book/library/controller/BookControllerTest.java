package com.book.library.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.book.library.model.Book;
import com.book.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;




@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Autowired
	private ObjectMapper objectMapper;
	@Test
	public void testGetAllBooks() throws Exception{
		Book book = new Book();
		book.setId(1L);
		book.setTitle("C#");
		book.setAuthor("Jon Skeet");
		book.setPublicationYear(2019);

		when(bookService.getAllBooks()).thenReturn(List.of(book));

		mockMvc.perform(get("/books"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].title").value("C#"));

	}
	@Test
	public void testCreateBook() throws Exception {
		Book book = new Book();
		book.setId(1L);
		book.setTitle("C#");
		book.setAuthor("Jon Skeet");
		book.setPublicationYear(2019);

		when(bookService.createBook(any(Book.class))).thenReturn(book);

		String json = objectMapper.writeValueAsString(book); 

		mockMvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.title").value("C#"));
	}

	@Test
	public void testGetBookById() throws Exception{
		Book book = new Book();
		book.setId(1L);
		book.setTitle("C#");
		book.setAuthor("Jon Skeet");
		book.setPublicationYear(2019);

		when(bookService.getBookByID(1L)).thenReturn(book);

		mockMvc.perform(get("/books/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title").value("C#"));
	}
	@Test
	public void testUpdateBook() throws Exception{
		Book book = new Book();
		book.setTitle("Java Script");
		book.setAuthor("Jon Skeet");
		book.setPublicationYear(1999);

		when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(book);

		String json = objectMapper.writeValueAsString(book); 

		mockMvc.perform(put("/books/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title").value("Java Script"));
	}

	@Test
	public void testDeleteBook() throws Exception{
		doNothing().when(bookService).deleteBookById(1L);

		mockMvc.perform(delete("/books/1"))
		.andExpect(status().isNoContent());

	}

}
