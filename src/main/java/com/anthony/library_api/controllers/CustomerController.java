package com.anthony.library_api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anthony.library_api.models.dtos.CustomerDTO;
import com.anthony.library_api.models.dtos.patching.PatchCustomerDTO;
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
	
	@GetMapping("/{id}")
	public Customer get(@PathVariable long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Customer create(@Valid @RequestBody CustomerDTO body) {
		return service.create(body);
	}
	
	@PutMapping("/{id}")
	public Customer update(@PathVariable long id, @Valid @RequestBody CustomerDTO body) {
		return service.update(id, body);
	}
	
	@PatchMapping("/{id}")
	public Customer patch(@PathVariable long id, @Valid @RequestBody PatchCustomerDTO body) {
		return service.patch(id, body);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		service.delete(id);
	}
	
}
