package com.anthony.library_api.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="loans")
public class Loan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="loaned_at", nullable=false)
	private LocalDateTime loanedAt;

	@Column(name="due_at", nullable=false)
	private LocalDateTime dueAt;
	
	@Column(name="returned_at")
	private LocalDateTime returnedAt;
	
	protected Loan() {}

	public Loan(Book book, Customer customer, User user, 
			LocalDateTime loanedAt, LocalDateTime dueAt, LocalDateTime returnedAt) 
	{
		this.book = book;
		this.customer = customer;
		this.user = user;
		this.loanedAt = loanedAt;
		this.dueAt = dueAt;
		this.returnedAt = returnedAt;
	}

	public long getId() { return id; }
	
	public Book getBook() { return book; }
	public void setBook(Book book) { this.book = book; }

	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }

	public User getUserId() { return user; }
	public void setUserId(User user) { this.user = user; }

	public LocalDateTime getLoanedAt() { return loanedAt; }
	public void setLoanedAt(LocalDateTime loanedAt) { this.loanedAt = loanedAt; }

	public LocalDateTime getDueAt() { return dueAt; }
	public void setDueAt(LocalDateTime dueAt) { this.dueAt = dueAt; }

	public LocalDateTime getReturnedAt() { return returnedAt; }
	public void setReturnedAt(LocalDateTime returnedAt) { this.returnedAt = returnedAt; }	
	
}
