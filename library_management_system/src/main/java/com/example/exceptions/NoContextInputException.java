package com.example.exceptions;

public class NoContextInputException extends RuntimeException{
    public NoContextInputException(String s) {
        super(s);
    }
}
