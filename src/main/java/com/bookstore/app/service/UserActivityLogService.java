package com.bookstore.app.service;

import com.bookstore.app.model.UserActivityLog;
import com.bookstore.app.repository.UserActivityLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityLogService {
	private final Logger log = LoggerFactory.getLogger(UserActivityLogService.class.getSimpleName());

	private final UserActivityLogRepository userActivityLogRepository;

	public UserActivityLogService(UserActivityLogRepository userActivityLogRepository) {
		this.userActivityLogRepository = userActivityLogRepository;
	}

	public List<UserActivityLog> getAllUserActivityLogs() {
		log.info("Retrieving all user activity logs");
		return userActivityLogRepository.findAll();
	}

	public UserActivityLog getUserActivityLogById(Long id) {
		log.info("Retrieving user activity log with id {}", id);
		return userActivityLogRepository.findById(id).orElse(null);
	}

	public UserActivityLog createUserActivityLog(UserActivityLog userActivityLog) {
		log.info("Creating new user activity log {}", userActivityLog);

		return userActivityLogRepository.save(userActivityLog);
	}

	public UserActivityLog updateUserActivityLog(Long id, UserActivityLog updatedUserActivityLog) {
		log.info("Updating user activity log with id {} to {}", id, updatedUserActivityLog);

		UserActivityLog existingUserActivityLog = userActivityLogRepository.findById(id).orElse(null);

		if (existingUserActivityLog != null) {
			existingUserActivityLog.setActivityLogs(updatedUserActivityLog.getActivityLogs());
			existingUserActivityLog.setUserId(updatedUserActivityLog.getUserId());
			userActivityLogRepository.save(existingUserActivityLog);
		}

		return null;
	}

	public void deleteUserActivityLog(Long id) {
		log.info("Deleting user activity log with id {}", id);

		userActivityLogRepository.deleteById(id);
	}
}
