package com.bookstore.app.service;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GraphQLService {
	private final Logger log = LoggerFactory.getLogger(GraphQLService.class.getSimpleName());

	private final GraphQL graphQL;

	public GraphQLService(GraphQL graphQL) {
		this.graphQL = graphQL;
	}

	public ResponseEntity<Object> executeQuery(String query) {
		log.info("Executing graphQL query {}", query);
		ExecutionResult executionResult = graphQL.execute(query);

		if (executionResult.getErrors().isEmpty()) {
			log.info("Successfully executed graphQL query");
			return ResponseEntity.ok(executionResult.getData());
		} else {
			log.info("Encountered error while executing graphQL query {}", executionResult.getErrors());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(executionResult.getErrors());
		}
	}
}
