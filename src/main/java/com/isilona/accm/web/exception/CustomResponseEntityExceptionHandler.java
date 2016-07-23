package com.isilona.accm.web.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isilona.accm.web.data.ErrorResponse;
//import com.isilona.bex.validation.exception.InvalidVerificationTokenException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        LOGGER.error(ex.getMessage(), ex);
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getDefaultMessage();
            errorResponse.addMessage(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getDefaultMessage();
            errorResponse.addMessage(error);
        }
        return errorResponse;
    }

    // @ResponseBody
    // @ExceptionHandler(InvalidVerificationTokenException.class)
    // public ErrorResponse exception(InvalidVerificationTokenException ex) {
    // LOGGER.error(ex.getMessage(), ex);
    // return new ErrorResponse();
    // }

}