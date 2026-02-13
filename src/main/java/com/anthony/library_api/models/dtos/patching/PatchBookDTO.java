package com.anthony.library_api.models.dtos.patching;

import jakarta.validation.constraints.Min;

public record PatchBookDTO(
		String title,
		String author,
		@Min(1) Long categoryId,
		@Min(0) Integer numberOfPages,
		@Min(0) Integer publicationYear
) {}
