package com.akagi.customer.service;

import java.util.List;

import com.akagi.customer.domain.Customer;

public interface CustomerService {

	/**
	 * Add new Custome  
	 * @param customer
	 * @return
	 */
	Customer createCustomer(Customer customer);
	
	/**
	 * Find Customer by Id
	 * @param id
	 * @return
	 * @throws ApiException
	 */
	Customer getCustomer(Long id) throws ApiException;
	
	/**
	 * List all the Customers
	 * @return
	 */
	List<Customer> getAllCustomer();
	
	/**
	 * Update customer
	 * @param id
	 * @param customer
	 * @return
	 * @throws ApiException
	 */
	Customer updateCustomer(Long id, Customer customer) throws ApiException;
	
	/**
	 * Delete customer by Id
	 * @param id
	 */
	void deleteCustomer(Long id);

	/**
	 * Find all customer by ZipCode
	 * @param zipCode
	 * @return
	 * @throws ApiException
	 */
	List<Customer> getCustomersByZipCode(String zipCode) throws ApiException;
}
