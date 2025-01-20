package com.example.corrency_converter.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime timestamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError(){
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String error,HttpStatus status){
        this();
        this.error = error;
        this.statusCode = status;
    }

}
