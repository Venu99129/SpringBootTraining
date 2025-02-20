package com.example.spring_security_assignment.advices;


import com.example.spring_security_assignment.exceptions.IntegerFormatException;
import com.example.spring_security_assignment.exceptions.MultipleUserLoginException;
import com.example.spring_security_assignment.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        List<String> subErrors = exception.getBindingResult()
                .getAllErrors()
                .stream().map(error-> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder().error("given parameters are not valid")
                .statusCode(HttpStatus.BAD_REQUEST).build();

        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception){
        List<String> subErrors = List.of(exception.getLocalizedMessage());

        ApiError apiError = ApiError.builder()
                .error("given json format is not valid format")
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

    @ExceptionHandler(IntegerFormatException.class)
    public ResponseEntity<ApiError> integerFormatExceptionHandler(IntegerFormatException exception){
        List<String> subErrors = List.of(exception.getMessage());
        ApiError apiError = ApiError.builder()
                .error("we except only integers")
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException exception){
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException exception){
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MultipleUserLoginException.class)
    public ResponseEntity<ApiError> handleMultipleUserLoginException(MultipleUserLoginException exception){
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
}
