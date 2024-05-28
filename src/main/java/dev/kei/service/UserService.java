package dev.kei.service;

import java.util.List;

import dev.kei.entity.User;
import dev.kei.repository.UserRepository;

public class UserService {

	public List<User> findAll() {
		UserRepository userRepository = new UserRepository();
		return userRepository.findAll();
	}

	public User findByUsername(String username) {
		UserRepository userRepository = new UserRepository();
		return userRepository.findByUsername(username);
	}

	public void save(User user) {
		UserRepository userRepository = new UserRepository();
		userRepository.save(user);
	}

	public void delete(int id) {
		UserRepository userRepository = new UserRepository();
		userRepository.delete(id);
	}
}