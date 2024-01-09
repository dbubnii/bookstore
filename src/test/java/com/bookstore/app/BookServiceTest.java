package com.bookstore.app;

import com.bookstore.app.model.Book;
import com.bookstore.app.repository.BookRepository;
import com.bookstore.app.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void createBookTest() {
		Book bookToCreate = new Book();
		bookToCreate.setTitle("Test Book");
		bookToCreate.setAuthor("Test Author");
		bookToCreate.setPrice(BigDecimal.valueOf(29.99));
		bookToCreate.setPublicationYear(2022);

		when(bookRepository.save(any(Book.class))).thenReturn(bookToCreate);

		Book createdBook = bookService.createBook(bookToCreate);

		assertEquals("Test Book", createdBook.getTitle());
		assertEquals("Test Author", createdBook.getAuthor());
		assertEquals(BigDecimal.valueOf(29.99), createdBook.getPrice());
		assertEquals(2022, createdBook.getPublicationYear());

		verify(bookRepository, times(1)).save(any(Book.class));
	}

	@Test
	void updateBookTest() {
		Long bookId = 1L;

		Book existingBook = new Book();
		existingBook.setId(bookId);
		existingBook.setTitle("Existing Book");
		existingBook.setAuthor("Existing Author");
		existingBook.setPrice(BigDecimal.valueOf(19.99));
		existingBook.setPublicationYear(2020);

		when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
		when(bookRepository.save(any(Book.class))).thenReturn(existingBook);

		Book updatedBook = new Book();
		updatedBook.setTitle("Updated Book");
		updatedBook.setAuthor("Updated Author");
		updatedBook.setPrice(BigDecimal.valueOf(24.99));
		updatedBook.setPublicationYear(2021);

		Book result = bookService.updateBook(bookId, updatedBook);

		assertEquals("Updated Book", result.getTitle());
		assertEquals("Updated Author", result.getAuthor());
		assertEquals(BigDecimal.valueOf(24.99), result.getPrice());
		assertEquals(2021, result.getPublicationYear());

		verify(bookRepository, times(1)).findById(bookId);
		verify(bookRepository, times(1)).save(any(Book.class));
	}

}
