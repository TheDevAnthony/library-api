package com.anthony.library_api.models.dtos.patching;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;


public record PatchLoanDTO(
		@Min(1) Long bookId,
		@Min(1) Long customerId,
		@Min(1) Long userId,
		LocalDateTime loanedAt,
		LocalDateTime dueAt,
		LocalDateTime returnedAt
) {}
