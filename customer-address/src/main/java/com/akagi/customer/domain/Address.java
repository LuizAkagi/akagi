package com.akagi.customer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ADDR_SEQ")
	private Long id;

	@Pattern(regexp = "[0-9]{5}-[0-9]{3}")
	private String zipCode;

	private int number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
