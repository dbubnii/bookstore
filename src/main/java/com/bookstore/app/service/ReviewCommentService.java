package com.bookstore.app.service;

import com.bookstore.app.model.ReviewComment;
import com.bookstore.app.repository.ReviewCommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewCommentService {
	private final Logger log = LoggerFactory.getLogger(ReviewCommentService.class.getSimpleName());

	private final ReviewCommentRepository reviewCommentRepository;

	public ReviewCommentService(ReviewCommentRepository reviewCommentRepository) {
		this.reviewCommentRepository = reviewCommentRepository;
	}

	public List<ReviewComment> getAllReviewComments() {
		log.info("Retrieving all reviewComments");
		return reviewCommentRepository.findAll();
	}

	public ReviewComment getReviewCommentByBookId(String id) {
		log.info("Retrieving reviewComment with id {}", id);

		return reviewCommentRepository.findById(id).orElse(null);
	}

	public ReviewComment createReviewComment(ReviewComment reviewComment) {
		log.info("Creating new reviewComment {}", reviewComment);

		return reviewCommentRepository.save(reviewComment);
	}

	public ReviewComment updateReviewComment(String id, ReviewComment updatedReviewComment) {
		log.info("Updating reviewComment with id {} to {}", id, updatedReviewComment);

		ReviewComment existingReviewComment = reviewCommentRepository.findById(id).orElse(null);

		if (existingReviewComment != null) {
			existingReviewComment.setComments(updatedReviewComment.getComments());
			existingReviewComment.setReviews(updatedReviewComment.getReviews());
			existingReviewComment.setBookId(updatedReviewComment.getBookId());
			reviewCommentRepository.save(existingReviewComment);
		}

		return null;
	}

	public void deleteReviewComment(String id) {
		log.info("Deleting reviewComment with id {}", id);

		reviewCommentRepository.deleteById(id);
	}
}
