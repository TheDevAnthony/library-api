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

import com.anthony.library_api.models.dtos.BookDTO;
import com.anthony.library_api.models.entities.Book;
import com.anthony.library_api.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

	private BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Book> getAll(@RequestParam Map<String, String> params) {
		String filter = params.get("by");
		String value = params.get("value");
		
		if (filter == null || value == null) {
			return service.findAll();
		}
		
		return switch (filter) {
			case "title" -> service.findAllByTitle(value);
			case "author" -> service.findAllByAuthor(value);
			case "category" -> service.findAllByCategory(value);
			default -> service.findAll();
		};
	}
	
	@GetMapping("/{id}")
	public Book get(@PathVariable long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Book create(@Valid @RequestBody BookDTO body) {
		return service.create(body);
	}
	
	@PutMapping("/{id}")
	public Book update(@PathVariable long id, @Valid @RequestBody BookDTO body) {
		return service.update(id, body);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}
	
}
