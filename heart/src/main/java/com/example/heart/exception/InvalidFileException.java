package com.example.heart.exception;

public class InvalidFileException extends RuntimeException {
    
    public InvalidFileException(String message) {
        super(message);
    }
}
