package com.anthony.library_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anthony.library_api.models.dtos.BookDTO;
import com.anthony.library_api.models.dtos.patching.PatchBookDTO;
import com.anthony.library_api.models.entities.Book;
import com.anthony.library_api.models.entities.BookCategory;
import com.anthony.library_api.repositories.BookCategoryRepository;
import com.anthony.library_api.repositories.BookRepository;
import com.anthony.library_api.utils.ServiceUtils;

@Service
public class BookService {
	
	private BookRepository bookRepo;
	private BookCategoryRepository bookCategoryRepo;
	
	public BookService(BookRepository bookRepo, BookCategoryRepository bookCategoryRepo) {
		this.bookRepo = bookRepo;
		this.bookCategoryRepo = bookCategoryRepo;
	}
	
	public List<Book> findAll() {
		return bookRepo.findAll();
	}
	
	public List<Book> findAllByAuthor(String author) {
		return bookRepo.findAllByAuthorContaining(author);
	}
	
	public List<Book> findAllByCategory(long categoryId) {
		return bookRepo.findAllByCategoryId(categoryId);
	}
	
	public List<Book> findAllByTitle(String title) {
		return bookRepo.findAllByTitleContaining(title);
	}
	
	public Book findById(long id) {
		return bookRepo.findById(id).orElseThrow(
				() -> new RuntimeException("Book not found"));
	}
	
	public Book create(BookDTO body) {
		Book book = new Book(
				body.title(),
				body.author(),
				bookCategoryRepo.findById(body.categoryId()).orElseThrow(
						() -> new RuntimeException("Book category not found")),
				body.numberOfPages(),
				body.publicationYear()
		);
		
		return bookRepo.save(book);
	}
	
	public Book update(long id, BookDTO body) {
		Book book = findById(id);
		
		book.setCategory(bookCategoryRepo.findById(body.categoryId()).orElseThrow(
				() -> new RuntimeException("Book category not found")));
		
		book = (Book) ServiceUtils.entityUpdate(book, body);
		return bookRepo.save(book);
	}
	
	public Book patch(long id, PatchBookDTO body) {
		Book book = findById(id);
		
		if (body.categoryId() != null) {
		    BookCategory category = bookCategoryRepo.findById(body.categoryId())
		        .orElseThrow(() -> new RuntimeException("Book category not found"));
		    book.setCategory(category);
		}

		book = (Book) ServiceUtils.entityUpdate(book, body);
		return bookRepo.save(book);
	}
	
	public void delete(long id) {
		bookRepo.deleteById(id);
	}
}
