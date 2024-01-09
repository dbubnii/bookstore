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
@Document(collation = "user_activity_logs")
public class UserActivityLog {
	@Id
	private Long userId;

	private List<LogEntry> activityLogs;

	@Getter
	@Setter
	static class LogEntry {
		private LocalDateTime timestamp;
		private String activityType;
		private String details;
	}
}
