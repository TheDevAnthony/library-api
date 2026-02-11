package com.anthony.library_api.models.dtos.auth;

import com.anthony.library_api.models.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
		@NotBlank String name,
		@NotBlank @Email String email,
		@NotBlank String password,
		@NotNull UserRole role
) {}
