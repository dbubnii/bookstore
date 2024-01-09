package com.bookstore.app;

import com.bookstore.app.service.GraphQLService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GraphQLServiceTest {
	@Mock
	private GraphQL graphQL;

	@InjectMocks
	private GraphQLService graphQLService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void executeQuerySuccessTest() {
		ExecutionResult executionResult = ExecutionResult.newExecutionResult().data(Collections.singletonMap("key", "value")).build();
		when(graphQL.execute(anyString())).thenReturn(executionResult);

		ResponseEntity<Object> response = graphQLService.executeQuery("query { test }");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(Collections.singletonMap("key", "value"), response.getBody());
	}

	@Test
	void executeQueryErrorTest() {
		GraphQLError graphQLError = mock(GraphQLError.class);
		when(graphQLError.getMessage()).thenReturn("GraphQL Error");

		ExecutionResult executionResult = ExecutionResult.newExecutionResult().errors(Collections.singletonList(graphQLError)).build();
		when(graphQL.execute(anyString())).thenReturn(executionResult);

		ResponseEntity<Object> response = graphQLService.executeQuery("query { test }");

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
