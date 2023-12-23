package com.example.javafxprojectforwork.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
