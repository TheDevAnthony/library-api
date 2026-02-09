package com.anthony.library_api.models.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerDTO(
		@NotBlank String name,
		@Email String email,
		@NotBlank String phone,
		@NotNull LocalDate birthday
) {}
