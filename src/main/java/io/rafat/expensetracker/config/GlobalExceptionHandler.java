package io.rafat.expensetracker.config;

import io.rafat.expensetracker.dto.ErrorResponse;
import io.rafat.expensetracker.utils.exception.AlreadyExistsException;
import io.rafat.expensetracker.utils.exception.NotFoundException;
import io.rafat.expensetracker.utils.exception.UnAuthorizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnAuthorizeException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorizeException(UnAuthorizeException e) {
        return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}
