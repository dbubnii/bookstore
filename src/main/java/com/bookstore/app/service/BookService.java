package com.bookstore.app.service;

import com.bookstore.app.model.Book;
import com.bookstore.app.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	private final Logger log = LoggerFactory.getLogger(BookService.class.getSimpleName());
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> getAllBooks() {
		log.info("Retrieving all books");
		return bookRepository.findAll();
	}

	public Book getBookById(Long id) {
		log.info("Retrieving book with id {}", id);
		return bookRepository.findById(id).orElse(null);
	}

	public Book createBook(Book book) {
		log.info("Creating new book {}", book);

		return bookRepository.save(book);
	}

	public Book updateBook(Long id, Book updatedBook) {
		log.info("Updating book with id {} to {}", id, updatedBook);
		Book existingBook = bookRepository.findById(id).orElse(null);

		if (existingBook != null) {
			existingBook.setAuthor(updatedBook.getAuthor());
			existingBook.setPrice(updatedBook.getPrice());
			existingBook.setTitle(updatedBook.getTitle());
			existingBook.setPublicationYear(updatedBook.getPublicationYear());
			bookRepository.save(existingBook);
			return existingBook;
		} else {
			return null;
		}
	}

	public void deleteBook(Long id) {
		log.info("Deleting book with id {}", id);

		bookRepository.deleteById(id);
	}
}
