package com.bookstore.app.controller;

import com.bookstore.app.service.GraphQLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graphql")
public class GraphQLController {
	private final GraphQLService graphQLService;

	public GraphQLController(GraphQLService graphQLService) {
		this.graphQLService = graphQLService;
	}

	@PostMapping
	public ResponseEntity<Object> executeQuery(@RequestBody String query) {
		return graphQLService.executeQuery(query);
	}

}
