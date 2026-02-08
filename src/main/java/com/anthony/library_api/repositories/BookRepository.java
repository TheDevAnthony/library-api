package com.anthony.library_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthony.library_api.models.BookCategory;
import com.anthony.library_api.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByTitleContaining(String title);
	
	List<Book> findAllByAuthor(String author);
	
	List<Book> findAllByCategory(BookCategory bookCategory);
}
