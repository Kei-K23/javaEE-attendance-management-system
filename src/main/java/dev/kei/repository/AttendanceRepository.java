package dev.kei.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import dev.kei.entity.User;
import dev.kei.util.DB;

public class AttendanceRepository {

	private static final Logger logger = Logger.getLogger(AttendanceRepository.class.getName());
//
//	public List<User> findAll() {
//		try (Connection connection = DB.getConnection();
//				Statement statement = connection.createStatement();
//				ResultSet rs = statement.executeQuery("SELECT * FROM attendnaces");
//
//		) {
//			logger.info("findAll::Retrieving users from the database...");
//			List<User> users = new ArrayList<>();
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String username = rs.getString("username");
//				String email = rs.getString("email");
//				String password = rs.getString("password");
//				int role = rs.getInt("role");
//				User user = new User(username, email, password);
//				user.setId(id);
//				user.setRole(role);
//
//				users.add(user);
//			}
//			return users;
//		} catch (Exception e) {
//			logger.severe("Error occurred while retrieving users: " + e.getMessage());
//			throw new RuntimeException("Something went wrong", e);
//		}
//	}
//
//	public User findByUsername(String username) {
//		try (Connection connection = DB.getConnection();
//				PreparedStatement preStat = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
//			preStat.setString(1, username);
//			User user = new User();
//			try (ResultSet rs = preStat.executeQuery()) {
//				logger.info("findByUsername::Retrieving user from the database...");
//				while (rs.next()) {
//					int id = rs.getInt("id");
//					String name = rs.getString("username");
//					String email = rs.getString("email");
//					String password = rs.getString("password");
//					int role = rs.getInt("role");
//
//					user.setId(id);
//					user.setUsername(name);
//					user.setEmail(email);
//					user.setPassword(password);
//					user.setRole(role);
//				}
//			}
//			return user;
//		} catch (Exception e) {
//			logger.severe("Error occurred while retrieving user: " + e.getMessage());
//			throw new RuntimeException("Something went wrong", e);
//		}
//	}

	public void save(User user) {
		try (Connection connection = DB.getConnection();
				PreparedStatement preStat = connection
						.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {
			preStat.setString(1, user.getUsername());
			preStat.setString(2, user.getEmail());
			preStat.setString(3, user.getPassword());
			// save user
			int rowsAffected = preStat.executeUpdate();
			logger.info("save::Creating user...");

			if (rowsAffected > 0) {
				logger.info("save::Creating user...");
			} else {
				logger.severe("save::Error occurred while creating user");
			}
		} catch (Exception e) {
			logger.severe("save::Error occurred while creating user: " + e.getMessage());
			throw new RuntimeException("save::Something went wrong", e);
		}
	}

	// Method to delete a user by ID
	public void delete(int id) {
		try (Connection connection = DB.getConnection();
				PreparedStatement preStat = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
			preStat.setInt(1, id);

			int rowsAffected = preStat.executeUpdate();
			if (rowsAffected > 0) {
				logger.info("delete::User with ID " + id + " deleted successfully.");
			} else {
				logger.severe("delete::No user found with ID " + id);
			}
		} catch (Exception e) {
			logger.severe("delete::Error occurred while deleting user with ID " + id + ": " + e.getMessage());
			throw new RuntimeException("Something went wrong", e);
		}
	}
}
