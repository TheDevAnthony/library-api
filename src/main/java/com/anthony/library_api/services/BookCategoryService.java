package com.anthony.library_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anthony.library_api.models.dtos.BookCategoryDTO;
import com.anthony.library_api.models.entities.BookCategory;
import com.anthony.library_api.repositories.BookCategoryRepository;

@Service
public class BookCategoryService {
	
	private BookCategoryRepository repo;
	
	public BookCategoryService(BookCategoryRepository repo) {
		this.repo = repo;
	}
	
	public List<BookCategory> findAll() {
		return repo.findAll();
	}
	
	public List<BookCategory> findAllByDescription(String description) {
		return repo.findAllByDescriptionContaining(description);
	}
	
	public BookCategory findById(long id) {
		return repo.findById(id).orElseThrow(
				() -> new RuntimeException("Book category not found"));
	}
	
	public BookCategory create(BookCategoryDTO body) {
		return repo.save(new BookCategory(body.description()));
	}
	
	public BookCategory update(long id, BookCategoryDTO body) {
		BookCategory category = findById(id);
		
		category.setDescription(body.description());
		return repo.save(category);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
