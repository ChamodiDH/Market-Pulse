package com.chamodidh.market_pulse.exceptions;

import com.chamodidh.market_pulse.exceptions.category.CategoryAlreadyExists;
import com.chamodidh.market_pulse.exceptions.category.CategoryNotFoundException;
import com.chamodidh.market_pulse.exceptions.item.*;
import com.chamodidh.market_pulse.exceptions.payment.InvalidPaymentAmount;
import com.chamodidh.market_pulse.exceptions.user.UserAlreadyExists;
import com.chamodidh.market_pulse.exceptions.user.UserDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // User-related exceptions
    @ExceptionHandler(UserDoesNotExistsException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserDoesNotExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Payment-related exceptions
    @ExceptionHandler(InvalidPaymentAmount.class)
    public ResponseEntity<String> handlePaymentProcessingException(InvalidPaymentAmount ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }


    // Item-related exceptions
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidItemDetailException.class)
    public ResponseEntity<String> handleInvalidItemDetailException(InvalidItemDetailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPaginationParameterException.class)
    public ResponseEntity<String> handleInvalidPaginationParameter(InvalidPaginationParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(InvalidSearchKeywordException.class)
    public ResponseEntity<String> handleSearchKeywordNotFoundException(InvalidSearchKeywordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SupplierNotFound.class)
    public ResponseEntity<String> handleSupplierNotFoundException(SupplierNotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //Category-related exceptions
    @ExceptionHandler(CategoryAlreadyExists.class)
    public ResponseEntity<String> handleCategoryAlreadyExistsException(CategoryAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Catch-all fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
