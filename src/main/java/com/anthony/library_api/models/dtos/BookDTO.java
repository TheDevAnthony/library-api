package com.anthony.library_api.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookDTO(
		@NotBlank String title,
		@NotBlank String author,
		@NotNull @Min(1) long categoryId,
		@NotNull @Min(0) int numberOfPages,
		@NotNull @Min(0) int publicationYear
) {}
