package com.anthony.library_api.models.dtos;

import com.anthony.library_api.models.BookCategory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookDTO(
		@NotBlank String title,
		@NotBlank String author,
		BookCategory category,
		@NotNull @Min(0) int numberOfPages,
		@NotNull @Min(0) int publicationYear
) {}
