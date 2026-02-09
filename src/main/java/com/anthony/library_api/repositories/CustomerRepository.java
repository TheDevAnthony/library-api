package com.anthony.library_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthony.library_api.models.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public List<Customer> findAllByNameContaining(String name);
	public List<Customer> findAllByEmailContaining(String email);
	public List<Customer> findAllByPhoneContaining(String phone);
	
}
