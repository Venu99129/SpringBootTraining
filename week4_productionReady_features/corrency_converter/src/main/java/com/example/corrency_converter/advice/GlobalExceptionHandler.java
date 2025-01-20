package com.example.corrency_converter.advice;

import com.example.corrency_converter.entities.CurrencyData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse<CurrencyData> missingServletRequestParameterExceptionHandling(MissingServletRequestParameterException exception){
        ApiError error = new ApiError(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        return new ApiResponse<>(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<CurrencyData>handleRuntimeException(RuntimeException exception){
        ApiError error = new ApiError(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        return new ApiResponse<>(error);
    }
}
