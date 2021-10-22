package com.akagi.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akagi.customer.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("SELECT c FROM Customer c left join c.addresses a WHERE a.zipCode = :zipCode")
	Optional<List<Customer>> findByZipCode(@Param("zipCode") String zipCode);
}

