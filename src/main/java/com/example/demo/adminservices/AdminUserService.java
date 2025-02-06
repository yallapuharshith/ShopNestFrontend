// Admin Service for Modify User functionality
package com.example.demo.adminservices;
import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.repository.JWTTokenRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminUserService {
	@Autowired
	 private final UserRepository userRepository;
	@Autowired
	 private final JWTTokenRepository jwtTokenRepository;

	    public AdminUserService(UserRepository userRepository, JWTTokenRepository jwtTokenRepository) {
	        this.userRepository = userRepository;
	        this.jwtTokenRepository = jwtTokenRepository;
	    }
	    
	    
	    @Transactional
	    public User modifyUser(Integer userId, String username, String email, String role) {
	    	 // Check if the user exists
	        Optional<User> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new IllegalArgumentException("User not found");
	        }
	        User existingUser = userOptional.get();
	        // Update user fields
	        if (username != null && !username.isEmpty()) {
	            existingUser.setUsername(username);
	        }
	        if (email != null && !email.isEmpty()) {
	            existingUser.setEmail(email);
	        }
	        if (role != null && !role.isEmpty()) {
	            try {
	                existingUser.setRole(Role.valueOf(role));
	            } catch (IllegalArgumentException e) {
	                throw new IllegalArgumentException("Invalid role: " + role);
	            }
	        }

	        // Delete associated JWT tokens
	        jwtTokenRepository.deleteByUserId(userId);

	        // Save updated user
	        return userRepository.save(existingUser);
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}