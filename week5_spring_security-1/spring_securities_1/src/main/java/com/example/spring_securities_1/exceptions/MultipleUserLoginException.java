package com.example.spring_securities_1.exceptions;

public class MultipleUserLoginException extends RuntimeException{

    public MultipleUserLoginException(String message){
        super(message);
    }
}
