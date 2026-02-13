package com.anthony.library_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthony.library_api.models.entities.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
	public List<BookCategory> findAllByDescriptionContaining(String description);
}
