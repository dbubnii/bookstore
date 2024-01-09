package com.bookstore.app.service;

import com.bookstore.app.model.User;
import com.bookstore.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	private final Logger log = LoggerFactory.getLogger(UserService.class.getSimpleName());

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		log.info("Retrieving all users");
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		log.info("Retrieving user by id {}", id);

		return userRepository.findById(id).orElse(null);
	}

	public User createUser(User user) {
		log.info("Creating new user {}", user);

		return userRepository.save(user);
	}

	public User updateUser(Long id, User updatedUser) {
		log.info("Updating user with id {} to {}", id, updatedUser);

		User existingUser = userRepository.findById(id).orElse(null);

		if (existingUser != null) {
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setUsername(updatedUser.getUsername());
			userRepository.save(existingUser);
		}

		return null;
	}

	public void deleteUser(Long id) {
		log.info("Deleting user with id {}", id);

		userRepository.deleteById(id);
	}
}
