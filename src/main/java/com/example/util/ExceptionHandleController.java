package com.example.util;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandleController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Validation Error");
        response.setErrorMessage("Invalid inputs.");
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> custNotFount(CustomerNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NotFound Exception");
        response.setErrorMessage("Customer Not Found Exception");
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> dataIntegrityIssue(DataIntegrityViolationException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Data integrity");
        response.setErrorMessage("Unique Key Constraint violated");
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
