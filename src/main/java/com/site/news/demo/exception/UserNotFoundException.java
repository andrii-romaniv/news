package com.site.news.demo.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("No user found witch id "+id);
    }

    public UserNotFoundException(String message) {
        super("User "+message +" mot found");
    }
}
