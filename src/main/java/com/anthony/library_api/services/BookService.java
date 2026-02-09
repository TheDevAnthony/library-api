package com.anthony.library_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anthony.library_api.models.BookCategory;
import com.anthony.library_api.models.dtos.BookDTO;
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
		return repo.findAllByAuthorContaining(author);
	}
	
	public List<Book> findAllByCategory(String category) {
		return repo.findAllByCategoryContaining(BookCategory.valueOf(category));
	}
	
	public List<Book> findAllByTitle(String title) {
		return repo.findAllByTitleContaining(title);
	}
	
	public Book findById(long id) {
		return repo.findById(id).orElse(null);
	}
	
	public Book create(BookDTO body) {
		Book book = new Book(
				body.title(),
				body.author(),
				body.category(),
				body.numberOfPages(),
				body.publicationYear()
		);
		
		return repo.save(book);
	}
	
	public Book update(long id, BookDTO body) {
		Book book = findById(id);
		
		if (book == null)
			throw new RuntimeException("Book not found");
		
		book.setTitle(body.title());
		book.setAuthor(body.author());
		book.setCategory(body.category());
		book.setNumberOfPages(body.numberOfPages());
		book.setPublicationYear(body.publicationYear());
		
		return repo.save(book);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
