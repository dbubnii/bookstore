package com.bookstore.app.config;

import com.bookstore.app.model.Book;
import com.bookstore.app.service.BookService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class GraphQLConfig {
	private final BookService bookService;

	public GraphQLConfig(BookService bookService) {
		this.bookService = bookService;
	}

	@Bean
	public GraphQL graphQL() throws IOException {
		SchemaParser schemaParser = new SchemaParser();
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(new ClassPathResource("graphql/schema.graphqls").getFile());

		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

		return GraphQL.newGraphQL(graphQLSchema).build();
	}

	private RuntimeWiring buildWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("getAllBooks", environment -> bookService.getAllBooks())
						.dataFetcher("getBookById", environment -> {
							Long id = environment.getArgument("id");
							return bookService.getBookById(id);
						}))
				.type("Mutation", typeWiring -> typeWiring
						.dataFetcher("createBook", environment -> {
							Book book = environment.getArgument("book");
							return bookService.createBook(book);
						})
						.dataFetcher("updateBook", environment -> {
							Long id = environment.getArgument("id");
							Book updatedBook = environment.getArgument("updatedBook");
							return bookService.updateBook(id, updatedBook);
						})
						.dataFetcher("deleteBook", environment -> {
							Long id = environment.getArgument("id");
							bookService.deleteBook(id);
							return true; // Indicate success
						}))
				.build();
	}
}
