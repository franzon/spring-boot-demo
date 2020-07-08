package com.example.demo.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("This email is already used: " + email);
    }
}
