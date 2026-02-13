package com.anthony.library_api.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book_categories")
public class BookCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="description", nullable=false, unique=true)
	private String description;
	
	protected BookCategory() {}
	
	public BookCategory(String description) {
		this.description = description;
	}
	
	public long getId() { return id; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
}
