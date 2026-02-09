package com.anthony.library_api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anthony.library_api.models.dtos.CustomerDTO;
import com.anthony.library_api.models.entities.Customer;
import com.anthony.library_api.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;
	
	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Customer> getAll(@RequestParam Map<String, String> params) {
		String filter = params.get("by");
		String value = params.get("value");
		
		if (filter == null || value == null) {
			return service.findAll();
		}
		
		return switch (filter) {
			case "name" -> service.findAllByName(value);
			case "email" -> service.findAllByEmail(value);
			case "phone" -> service.findAllByPhone(value);
			default -> service.findAll();
		};
	}
	
	@GetMapping("/{customerId}")
	public Customer getById(@PathVariable long customerId) {
		return service.findById(customerId);
	}
	
	@PostMapping
	public Customer create(@Valid @RequestBody CustomerDTO body) {
		return service.create(body);
	}
	
	@PutMapping("/{customerId}")
	public Customer update(@PathVariable long customerId, @Valid @RequestBody CustomerDTO body) {
		return service.update(customerId, body);
	}
	
	@DeleteMapping("/{customerId}")
	public void delete(@PathVariable long customerId) {
		service.delete(customerId);
	}
	
}
