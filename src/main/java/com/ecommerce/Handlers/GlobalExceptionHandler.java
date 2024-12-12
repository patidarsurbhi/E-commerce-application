package com.ecommerce.Handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.exception.NotblankException;
import com.ecommerce.exception.ResourceAlreadyExistException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.response.ErrorResponse;


/**
 * Global exception handler to manage exceptions and provide appropriate HTTP
 * responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	

	/**
	 * Handles ResourceNotFoundException and returns a 404 Not Found response.
	 *
	 * @param rnfe the ResourceNotFoundException
	 * @return ResponseEntity with error details
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException rnfe) {
		return new ResponseEntity<>(
				new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, rnfe.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> resourceAlreadyExistException(ResourceAlreadyExistException raee) {
		return new ResponseEntity<>(
				new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE, raee.getMessage()),
				HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(NotblankException.class)
	public ResponseEntity<ErrorResponse> notBlankException(NotblankException raee) {
		return new ResponseEntity<>(
				new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE, raee.getMessage()),
				HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	/**
	 * Handles MethodArgumentNotValidException and returns a 400 Bad Request
	 * response with validation errors.
	 *
	 * @param ex the MethodArgumentNotValidException
	 * @return ResponseEntity with error details
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		ErrorResponse errorRes = new ErrorResponse();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			errorRes.setMessage(error.getDefaultMessage());
			errorRes.setError(HttpStatus.BAD_REQUEST);
			errorRes.setStatus(HttpStatus.BAD_REQUEST.value());
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
	}
	
}
