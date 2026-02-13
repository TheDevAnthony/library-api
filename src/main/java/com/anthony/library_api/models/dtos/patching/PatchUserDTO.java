package com.anthony.library_api.models.dtos.patching;

import jakarta.validation.constraints.Email;

public record PatchUserDTO(
		String name,
		@Email String email,
		String password
) {}
