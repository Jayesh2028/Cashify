package com.cashify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		StringBuilder errorMessage = new StringBuilder();
		result.getFieldErrors().forEach(error -> {
			errorMessage.append(error.getDefaultMessage()).append("; ");
		});
		return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	}

}
