package com.akagi.customer.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.akagi.customer.domain.Customer;
import com.akagi.customer.service.ApiException;
import com.akagi.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService; 
	
	@PostMapping
	public @ResponseBody ResponseEntity<Customer> createCustumer(@Valid @RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerService.createCustomer(customer), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<Customer> getCustomer(@PathVariable Long id) throws ApiException{
		return ResponseEntity.ok(customerService.getCustomer(id));
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Customer>> getCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomer());
	}
	
	@PutMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) throws ApiException{
		return ResponseEntity.ok(customerService.updateCustomer(id, customer));
	}
	
	@DeleteMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<String> deleteCustomer(@PathVariable Long id){
		customerService.deleteCustomer(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/zipCode/{zipCode}")
	public @ResponseBody ResponseEntity<List<Customer>> getCustomerByZipCode(@PathVariable String zipCode) throws ApiException {
		return ResponseEntity.ok(customerService.getCustomersByZipCode(zipCode));
	}
}
