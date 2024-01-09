package com.bookstore.app;

import com.bookstore.app.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookStoreApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void getAllBooksTest() {
		ResponseEntity<String> response = restTemplate.getForEntity(createURL("/api/books"), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void getBookByIdTest() {
		ResponseEntity<String> response = restTemplate.getForEntity(createURL("/api/books/1"), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void createBookTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

		// Create a book request object
		Book bookRequest = new Book(1L, "New Book", "New Author", new BigDecimal("24.99"), 2022);

		ResponseEntity<String> response = restTemplate.postForEntity(createURL("/api/books"), new HttpEntity<>(bookRequest, headers), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	private String createURL(String uri) {
		return "http://localhost:" + port + uri;
	}
}
