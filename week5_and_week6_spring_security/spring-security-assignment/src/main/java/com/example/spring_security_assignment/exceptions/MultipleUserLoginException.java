package com.example.spring_security_assignment.exceptions;

public class MultipleUserLoginException extends RuntimeException{

    public MultipleUserLoginException(String message){
        super(message);
    }
}
