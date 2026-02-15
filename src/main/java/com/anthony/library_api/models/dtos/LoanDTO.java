package com.anthony.library_api.models.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LoanDTO(
		@NotNull @Min(1) long bookId,
		@NotNull @Min(1) long customerId,
		@NotNull @Min(1) long userId,
		@NotNull LocalDateTime loanedAt,
		@NotNull LocalDateTime dueAt,
		LocalDateTime returnedAt
) {}
