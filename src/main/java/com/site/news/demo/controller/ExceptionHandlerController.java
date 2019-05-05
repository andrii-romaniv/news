package com.site.news.demo.controller;

import com.site.news.demo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public void UserHandleNotFound(HttpServletResponse response)throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
