package com.anthony.library_api.controllers.admin;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anthony.library_api.models.dtos.UserDTO;
import com.anthony.library_api.models.dtos.auth.RegisterDTO;
import com.anthony.library_api.models.entities.User;
import com.anthony.library_api.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
	
	private UserService service;

	public AdminUserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public User get(long id) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<User> getAll() {
		return service.findAll();
	}

	@PostMapping
	public User create(@Valid @RequestBody RegisterDTO body) {
		return service.create(body);
	}
	
	@PutMapping("/{id}") 
	public User update(long id, @Valid @RequestBody UserDTO body){
		return service.update(id, body);
	}
	
	@DeleteMapping("/{id}")
	public void delete(long id) {
		service.delete(id);
	}
}
