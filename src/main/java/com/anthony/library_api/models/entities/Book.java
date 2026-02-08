package com.anthony.library_api.models.entities;

import com.anthony.library_api.models.BookCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="author", nullable=false)
	private String author;
	
	@Column(name="category")
	private BookCategory category;
	
	@Column(name="number_of_pages", nullable=false)
	private int numberOfPages;
	
	@Column(name="publication_year", nullable=false)
	private int publicationYear;

	protected Book() {}
	
	public Book(long id, String title, String author, BookCategory category, int numberOfPages, int publicationYear) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.numberOfPages = numberOfPages;
		this.publicationYear = publicationYear;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	
}
