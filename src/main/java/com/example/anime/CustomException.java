package com.example.anime;

/**
 * My custom exception class.
 */
public class CustomException extends Throwable {
    private String message;

    public CustomException(String message) {
        this.message = message;
    }
}