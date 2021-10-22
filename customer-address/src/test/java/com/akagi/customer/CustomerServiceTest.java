package com.akagi.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.akagi.customer.domain.Customer;
import com.akagi.customer.repository.CustomerRepository;
import com.akagi.customer.service.ApiException;
import com.akagi.customer.service.DefaultCustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest extends BaseTest {

	private static final String NOT_ABLE_TO_FIND_THE_REFERECE_BY_ID = "Not able to find the customer by id";
	
	@InjectMocks
	private DefaultCustomerService service;

	@Mock
	private CustomerRepository respository;

	@BeforeEach
	void init() {
		setupCustomer();
	}

	@Test
	void createCustomerTest() {

		when(respository.save(any(Customer.class))).thenReturn(customer);
		Customer response = service.createCustomer(customer);
	
		assertNotNull(response);
	}

	@Test
	void getCustomerTest() throws ApiException {

		when(respository.findById(anyLong())).thenReturn(optionalCustomer);
		Customer response = service.getCustomer(1L);
	
		assertNotNull(response);
	}
	
	@Test
	void getCustomerWithErrorTest()  throws ApiException{
		when(respository.findById(anyLong())).thenReturn(emptyOptionalCustomer);
		ApiException exception = assertThrows(ApiException.class, () -> {
			service.getCustomer(1L);
        });
		
		assertEquals(NOT_ABLE_TO_FIND_THE_REFERECE_BY_ID, exception.getMessage());
		
	}
	
}
