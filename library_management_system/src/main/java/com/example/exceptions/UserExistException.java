package com.example.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException(String s) {
        super(s);
    }
}
