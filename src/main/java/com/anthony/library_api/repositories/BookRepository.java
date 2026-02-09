package com.anthony.library_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthony.library_api.models.BookCategory;
import com.anthony.library_api.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	public List<Book> findAllByTitleContaining(String title);
	public List<Book> findAllByAuthorContaining(String author);	
	public List<Book> findAllByCategoryContaining(BookCategory bookCategory);
}
