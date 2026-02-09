package com.anthony.library_api.models.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone", nullable=false)
	private String phone;
	
	@Column(name="birthday", nullable=false)
	private LocalDate birthday;
	
	protected Customer() {}

	public Customer(String name, String email, String phone, LocalDate birthday) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
	}

	public long getId() { return id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }

	public LocalDate getBirthday() { return birthday; }
	public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
	
}
