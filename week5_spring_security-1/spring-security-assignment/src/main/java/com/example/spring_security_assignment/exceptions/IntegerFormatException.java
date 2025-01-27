package com.example.spring_security_assignment.exceptions;

import java.io.IOException;

public class IntegerFormatException extends IOException {
    public IntegerFormatException(String message) {
        super(message);
    }
}
