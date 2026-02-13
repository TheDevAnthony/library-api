package com.anthony.library_api.models.dtos.patching;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;

public record PatchCustomerDTO(
		String name,
		@Email String email,
		String phone,
		LocalDate birthday
) {}
