package com.anthony.library_api.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record BookCategoryDTO(
		@NotBlank String description
) {}
