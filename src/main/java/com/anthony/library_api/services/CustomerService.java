package com.anthony.library_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anthony.library_api.models.dtos.CustomerDTO;
import com.anthony.library_api.models.entities.Customer;
import com.anthony.library_api.repositories.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repo;
	
	public CustomerService(CustomerRepository repo) {
		this.repo = repo;
	}
	
	public List<Customer> findAll() {
		return repo.findAll();
	}
	
	public List<Customer> findAllByName(String name) {
		return repo.findAllByNameContaining(name);
	}
	
	public List<Customer> findAllByEmail(String email) {
		return repo.findAllByEmailContaining(email);
	}

	public List<Customer> findAllByPhone(String phone) {
		return repo.findAllByPhoneContaining(phone);
	}
	
	public Customer findById(long id) {
		return repo.findById(id).orElse(null);
	}
	
	public Customer create(CustomerDTO body) {
		Customer customer = new Customer(
					body.name(),
					body.email(),
					body.phone(),
					body.birthday()
				);
		
		return repo.save(customer);
	}
	
	public Customer update(long id, CustomerDTO body) {
		Customer customer = findById(id);
		
		if (customer == null) 
			throw new RuntimeException("Customer not found");
		
		customer.setName(body.name());
		customer.setEmail(body.email());
		customer.setPhone(body.phone());
		customer.setBirthday(body.birthday());
		
		return repo.save(customer);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
