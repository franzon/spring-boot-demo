package com.example.demo.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Access to this resource is not authorized to authenticated user");
    }
}
