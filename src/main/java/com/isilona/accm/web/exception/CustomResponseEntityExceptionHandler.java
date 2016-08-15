package com.isilona.accm.web.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isilona.accm.web.data.ErrorHolder;
import com.isilona.accm.web.data.ErrorResponse;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(HttpServletRequest request,
	    MethodArgumentNotValidException ex) {
	LOGGER.error(ex.getMessage(), ex);
	List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
	List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
	ErrorResponse errorResponse = new ErrorResponse();
	for (FieldError fieldError : fieldErrors) {
	    ErrorHolder errorHolder = new ErrorHolder();

	    errorHolder.setField(fieldError.getField());
	    errorHolder.setErrorMessage(fieldError.getDefaultMessage());

	    errorResponse.addErrorHolder(errorHolder);
	}

	for (ObjectError objectError : globalErrors) {

	    ErrorHolder errorHolder = new ErrorHolder();

	    errorHolder.setErrorMessage(objectError.getDefaultMessage());

	    errorResponse.addErrorHolder(errorHolder);

	}

	errorResponse.setUrl(request.getRequestURL().toString());
	return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // @ResponseBody
    // @ExceptionHandler(InvalidVerificationTokenException.class)
    // public ErrorResponse exception(InvalidVerificationTokenException ex) {
    // LOGGER.error(ex.getMessage(), ex);
    // return new ErrorResponse();
    // }

}