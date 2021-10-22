package com.akagi.customer;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import com.akagi.customer.domain.Address;
import com.akagi.customer.domain.Customer;

public abstract class BaseTest {

	Customer customer;
	
	Optional<Customer> emptyOptionalCustomer = Optional.empty();
	
	Optional<Customer> optionalCustomer;
	
	void setupCustomer() {
		Address address = new Address();
		address.setZipCode("12345-123");
		address.setNumber(10);

		customer = new Customer();
		customer.setAge(25);
		customer.setDocumentId("12345");
		customer.setName("João");
		customer.setRegistrationDate(LocalDateTime.now());
		customer.setAddresses(Collections.singletonList(address));
		
		optionalCustomer = Optional.of(customer);
	}

}
