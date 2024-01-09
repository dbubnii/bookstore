package com.bookstore.app.repository;

import com.bookstore.app.model.ReviewComment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewCommentRepository extends MongoRepository<ReviewComment, String> {
}
