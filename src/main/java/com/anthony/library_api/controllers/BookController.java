package com.anthony.library_api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anthony.library_api.models.dtos.book.BookDTO;
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
			case "author" -> service.findAllByAuthor(value);
			case "category" -> service.findAllByCategory(value);
			default -> service.findAll();
		};
	}
	
	@GetMapping("/id/{bookId}")
	public Book getById(@PathVariable long bookId) {
		return service.findById(bookId);
	}
	
	@GetMapping("/title/{bookTitle}")
	public Book getByTitle(@PathVariable String bookTitle) {
		return service.findByTitle(bookTitle);
	}
	
	@PostMapping
	public Book createBook(@Valid @RequestBody BookDTO body) {
		Book book = new Book(
				0,
				body.title(),
				body.author(),
				body.category(),
				body.numberOfPages(),
				body.publicationYear()
		);
		
		return service.save(book);
	}
	
	@PutMapping("/{bookId}")
	public Book updateBook(@PathVariable long bookId, @Valid @RequestBody BookDTO body) {
		Book book = service.findById(bookId);
		
		book.setTitle(body.title());
		book.setAuthor(body.author());
		book.setCategory(body.category());
		book.setNumberOfPages(body.numberOfPages());
		book.setPublicationYear(body.publicationYear());
		
		return service.save(book);
	}
}
