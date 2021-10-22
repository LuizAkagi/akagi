package com.akagi.customer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.akagi.customer.domain.Customer;
import com.akagi.customer.repository.CustomerRepository;

@Service
public class DefaultCustomerService implements CustomerService {

	private static final String NOT_ABLE_TO_FIND_THE_REFERECE_BY_ZIP_CODE = "Not able to find any customer by zipCode";
	private static final String NOT_ABLE_TO_FIND_THE_REFERECE_BY_ID = "Not able to find the customer by id";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(@Validated Customer customer) {
		customer.setLastUpdateDate(LocalDateTime.now());
		Customer newCustomer = customerRepository.save(customer);
		logger.info("New Customer created customer={}", newCustomer);
		return newCustomer;
	}

	@Override
	public Customer getCustomer(Long id) throws ApiException {
		logger.info("Getting customer id={}", id);
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new ApiException(NOT_ABLE_TO_FIND_THE_REFERECE_BY_ID);
		}

	}

	@Override
	public List<Customer> getAllCustomer() {
		logger.info("Getting all customers");
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(Long id, @Validated Customer customer) throws ApiException {
		logger.info("Updating customerId={}, customer={}", id, customer);
		Optional<Customer> currentCustomer = customerRepository.findById(id);
		if (currentCustomer.isPresent()) {
			currentCustomer.get().setDocumentId(customer.getDocumentId());
			currentCustomer.get().setName(customer.getName());
			currentCustomer.get().setAge(customer.getAge());
			currentCustomer.get().setRegistrationDate(customer.getRegistrationDate());
			currentCustomer.get().setLastUpdateDate(LocalDateTime.now());
			return customerRepository.save(currentCustomer.get());
		} else {
			throw new ApiException(NOT_ABLE_TO_FIND_THE_REFERECE_BY_ID);
		}
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<Customer> getCustomersByZipCode(String zipCode) throws ApiException {
		logger.info("Getting customers by zipCode={}", zipCode);
		Optional<List<Customer>> customers = customerRepository.findByZipCode(zipCode);
		if (customers.isPresent()) {
			return customers.get();
		} else {
			throw new ApiException(NOT_ABLE_TO_FIND_THE_REFERECE_BY_ZIP_CODE);
		}

	}

}
