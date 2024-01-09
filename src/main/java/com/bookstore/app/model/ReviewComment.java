package com.bookstore.app.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "review_comments")
public class ReviewComment {
	@Id
	private String bookId;

	private List<Comment> comments;
	private List<Review> reviews;

	@Getter
	@Setter
	static class Comment {
		private Long userId;
		private String text;
		private LocalDateTime timestamp;
	}

	@Getter
	@Setter
	static class Review {
		private Long userId;
		private int rating;
		private String text;
		private LocalDateTime timestamp;

	}
}
