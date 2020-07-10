package com.example.demo.exception;

public class MusicNotFoundException extends RuntimeException {
    public MusicNotFoundException(Long id) {
        super("This music was not found: " + id);
    }
}
