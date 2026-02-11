package com.anthony.library_api.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anthony.library_api.models.dtos.UserDTO;
import com.anthony.library_api.models.dtos.auth.RegisterDTO;
import com.anthony.library_api.models.entities.User;
import com.anthony.library_api.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository repo;
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User findById(long id) {
		return repo.findById(id).orElse(null);
	}
	
	public User findByEmail(String email) {
		return repo.findByEmail(email).orElse(null);
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User create(RegisterDTO body) {
		User user = new User(
					body.name(),
					body.email(),
					passwordEncoder.encode(body.password()),
					body.role()
				);
		
		return repo.save(user);
	}
	
	public User update(long id, UserDTO body) {
		User user = findById(id);
		
		if (user == null) 
			throw new RuntimeException("User not found");
		
		user.setName(body.name());
		user.setEmail(body.email());
		user.setPassword(passwordEncoder.encode(body.password()));
		
		return repo.save(user);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
