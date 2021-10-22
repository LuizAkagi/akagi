package com.akagi.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.akagi.customer.domain.Address;
import com.akagi.customer.domain.Customer;
import com.akagi.customer.rest.CustomerController;
import com.akagi.customer.service.ApiException;
import com.akagi.customer.service.CustomerService;

@SpringBootTest
class CustomerApplicationTests {

	@InjectMocks
	private CustomerController controller;

	@Mock
	private CustomerService service;
	
	private Address address;
	
	private Customer customer;
	
	@BeforeEach
	void init() {
		address = new Address();
		address.setZipCode("12345-123");
		address.setNumber(10);
		
		customer = new Customer();
		customer.setAge(25);
		customer.setDocumentId("12345");
		customer.setName("João");
		customer.setRegistrationDate(LocalDateTime.now());
		customer.setAddresses(Collections.singletonList(address));
	}
	
	@Test
	void createCustumer() {
		
		
		
		when(service.createCustomer(any(Customer.class))).thenReturn(customer);

		ResponseEntity<Customer> response = controller.createCustumer(customer);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void getCustomer() throws ApiException {
		
		when(service.getCustomer(anyLong())).thenReturn(customer);
		ResponseEntity<Customer> response = controller.getCustomer(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void getCustomers() throws ApiException {
		
		when(service.getAllCustomer()).thenReturn(Collections.singletonList(customer));
		ResponseEntity<List<Customer>> response = controller.getCustomers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void updateCustomer() throws ApiException {
		
		when(service.updateCustomer(anyLong(), any(Customer.class))).thenReturn(customer);
		ResponseEntity<Customer> response = controller.updateCustomer(1L, customer);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void deleteCustomer() throws ApiException {
		
		ResponseEntity<String> response = controller.deleteCustomer(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void getCustomerByZipCode() throws ApiException {
		when(service.getCustomersByZipCode(anyString())).thenReturn(Collections.singletonList(customer));
		ResponseEntity<List<Customer>> response = controller.getCustomerByZipCode("12345-123");
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
