package com.akagi.customer.rest;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.akagi.customer.service.ApiException;

@ControllerAdvice
public class CustomerExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<String> applicationException(ApiException apiException) {
		logger.error("Application Exception", apiException);
			return new ResponseEntity<>(apiException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> databaseConstraintViolation(ConstraintViolationException exception) {
		logger.error("Database issue.", exception);

		return new ResponseEntity<>("The document id must be unique.", HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> databaseResultViolation(EmptyResultDataAccessException exception) {
		logger.error("Database issue.", exception);

		return new ResponseEntity<>("Not able to remove the customer.", HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public ResponseEntity<String> handleValidationError(javax.validation.ConstraintViolationException exception) {
		logger.error("Field validation error.", exception);

		StringBuilder sb = new StringBuilder();

		Set<ConstraintViolation<?>> validations = exception.getConstraintViolations();
		for (ConstraintViolation<?> validation : validations) {
			sb.append(validation.getPropertyPath() + " " + validation.getMessage());
		}

		return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
	}
	
}
