package com.bookstore.app.repository;

import com.bookstore.app.model.UserActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserActivityLogRepository extends MongoRepository<UserActivityLog, Long> {
}
