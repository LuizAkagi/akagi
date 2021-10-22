package com.akagi.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akagi.customer.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
