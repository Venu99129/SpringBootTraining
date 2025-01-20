package com.example.corrency_converter.advice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    ApiResponse(){ this.timeStamp = LocalDateTime.now();}

    public ApiResponse(T data){this(); this.data= data;}

    ApiResponse(ApiError error){ this(); this.error = error;}
}
