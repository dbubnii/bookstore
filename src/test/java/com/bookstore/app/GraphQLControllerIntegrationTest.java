package com.bookstore.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class GraphQLControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAllBooksGraphQLTest() throws Exception {
		String query = "query { getAllBooks { id title author } }";

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"query\":\"" + query + "\"}"))
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(200, response.getStatus());
	}

	@Test
    void createBookGraphQLTest() throws Exception {
        String mutation =
                """
                   mutation { createBook(book: { title: "New Book", author: "New Author", price: 24.99, publicationYear: 2022 }) { id title author } }
                """;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"query\":\"" + mutation + "\"}"))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(400, response.getStatus());
    }

}
