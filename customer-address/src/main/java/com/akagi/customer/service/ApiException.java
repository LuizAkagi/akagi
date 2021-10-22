package com.akagi.customer.service;

@SuppressWarnings("serial")
public class ApiException extends Exception{
	
	public ApiException(String errorMessage) {
        super(errorMessage);
    }
	
}
