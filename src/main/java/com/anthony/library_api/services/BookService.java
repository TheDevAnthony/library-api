package com.anthony.library_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anthony.library_api.models.BookCategory;
import com.anthony.library_api.models.entities.Book;
import com.anthony.library_api.repositories.BookRepository;

@Service
public class BookService {
	
	private BookRepository repo;
	
	public BookService(BookRepository repo) {
		this.repo = repo;
	}
	
	public List<Book> findAll() {
		return repo.findAll();
	}
	
	public List<Book> findAllByAuthor(String author) {
		return repo.findAllByAuthor(author);
	}
	
	public List<Book> findAllByCategory(String category) {
		return repo.findAllByCategory(BookCategory.valueOf(category));
	}
	
	public Book findById(long id) {
		return repo.findById(id).orElse(null);
	}
	
	public Book findByTitle(String title) {
		return repo.findByTitleContaining(title).orElse(null);
	}
	
	public Book save(Book book) {
		return repo.save(book);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
