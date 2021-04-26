package com.pubsap.cardService.Exeption.Handler;

import com.pubsap.cardService.Exeption.NoRecordFoundException;
import com.pubsap.cardService.Exeption.ValidationFailureException;
import com.pubsap.cardService.validation.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CardExceptionHandler {
    /**
     * Validation on Card Number fails
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ValidationFailureException.class)
    public ResponseEntity<ErrorResponse> validationFailureExceptionHandler(
            ValidationFailureException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("API_01_LUHN");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Records not found exception response entity.
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ErrorResponse> noRecordFoundExceptionHandler(
            NoRecordFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("API_02_EMPTY");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Globle excpetion handler response entity.
     * @param ex      the ex
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globleExcpetionHandler(Exception ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("API_03_ERROR");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
