package com.cg.gasbooking.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<ExceptionResponse> handleDuplicateIdException(DuplicateIdException e) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("CONFLICT");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(response,
				HttpStatus.CONFLICT);

		return responseEntity;
	}

	@ExceptionHandler(value = AdminNotFoundException.class)
	public ResponseEntity<ExceptionResponse> AdminNotFoundException(AdminNotFoundException e) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("CONFLICT");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(response,
				HttpStatus.CONFLICT);

		return responseEntity;
	}

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<ExceptionResponse> CustomerNotFoundException(CustomerNotFoundException e) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("CONFLICT");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(response,
				HttpStatus.CONFLICT);

		return responseEntity;
	}

	@ExceptionHandler(value = GasBookingNotFoundException.class)
	public ResponseEntity<ExceptionResponse> GasBookingNotFoundException(GasBookingNotFoundException e) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("CONFLICT");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(response,
				HttpStatus.CONFLICT);

		return responseEntity;
	}

	@ExceptionHandler(value = SurrenderCylinderNotFoundException.class)
	public ResponseEntity<ExceptionResponse> SurrenderCylinderNotFoundException(SurrenderCylinderNotFoundException e) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("CONFLICT");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse>(response,
				HttpStatus.CONFLICT);

		return responseEntity;
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) 
	{
		ExceptionResponse response=new ExceptionResponse();
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		String errormessage=null;
		for(String key:errors.keySet())
		{
			errormessage=errors.get(key);
		}
		response.setErrorCode("UNSUPPORTED_mEDIA_TYPE");
		response.setErrorMessage(errormessage);
		response.setTimestamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		
		return responseEntity;
	}

}
