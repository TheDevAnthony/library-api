package com.anthony.library_api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anthony.library_api.models.dtos.BookCategoryDTO;
import com.anthony.library_api.models.entities.BookCategory;
import com.anthony.library_api.services.BookCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books/categories")
public class BookCategoryController {
	
	private BookCategoryService service;
	
	public BookCategoryController(BookCategoryService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<BookCategory> getAll(@RequestParam Map<String, String> params) {
		String filter = params.get("by");
		String value = params.get("value");
		
		if (filter == null || value == null) {
			return service.findAll();
		}
		
		return switch (filter) {
			case "description" -> service.findAllByDescription(value);
			default -> service.findAll();
		};
	}
	
	@GetMapping("/{id}")
	public BookCategory get(@PathVariable long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public BookCategory create(@Valid @RequestBody BookCategoryDTO body) {
		return service.create(body);
	}
	
	@PutMapping("/{id}")
	public BookCategory update(@PathVariable long id, @Valid @RequestBody BookCategoryDTO body) {
		return service.update(id, body);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}
}
