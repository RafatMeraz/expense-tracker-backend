package io.rafat.expensetracker.config;

import io.rafat.expensetracker.dto.ErrorResponse;
import io.rafat.expensetracker.utils.exception.AlreadyExistsException;
import io.rafat.expensetracker.utils.exception.BadRequestException;
import io.rafat.expensetracker.utils.exception.NotFoundException;
import io.rafat.expensetracker.utils.exception.UnAuthorizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> Map.of(fieldError.getField(), Objects.requireNonNull(fieldError.getDefaultMessage())))
                .toList();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errors(errors)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
