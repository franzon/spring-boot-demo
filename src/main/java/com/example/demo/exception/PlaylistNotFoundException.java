package com.example.demo.exception;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(Long id) {
        super("This playlist was not found: " + id);
    }
}
