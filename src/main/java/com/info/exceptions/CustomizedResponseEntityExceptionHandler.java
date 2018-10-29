package com.info.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 * @author vijay
 *
 *Centralized Exception handling for common exceptions
 *
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> errors = bindingResult.getFieldErrors();
		List<String> message = new ArrayList<>();
		for (FieldError e : errors) {
			message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
		}

		ErrorMessage errorDetails = new ErrorMessage(new Date(), "Invalid inputs", message.toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);

	}

	@ExceptionHandler(RiskPreferenceNotValidException.class)
	public final ResponseEntity<ErrorMessage> handleUserNotFoundException(RiskPreferenceNotValidException ex,
			WebRequest request) {
		ErrorMessage errorDetails = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorMessage errorDetails = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
