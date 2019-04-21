package com.example.demo.exception;

public class WrongInputException extends Exception {
    public WrongInputException() {
    }

    public WrongInputException(String message) {
        super(message);
    }
}
