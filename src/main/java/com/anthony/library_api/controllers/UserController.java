package com.anthony.library_api.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anthony.library_api.models.dtos.UserDTO;
import com.anthony.library_api.models.dtos.patching.PatchUserDTO;
import com.anthony.library_api.models.entities.User;
import com.anthony.library_api.security.UserDetailsImpl;
import com.anthony.library_api.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping
	public User get(Authentication auth) {
		UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
		return service.findById(user.getId());
	}
	
	@PutMapping
	public User update(Authentication auth, @Valid @RequestBody UserDTO body) {
		UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
		return service.update(user.getId(), body);
	}
	
	@PatchMapping
	public User patch(Authentication auth, @Valid @RequestBody PatchUserDTO body) {
		UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
		return service.patch(user.getId(), body);
	}
	
}
