package com.site.news.demo.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
@Controller
public class ErrorPageController implements ErrorController {

    @RequestMapping(value = "/error")
    public String handleError(HttpServletResponse response ,Model model){
        String errorMessage;
        if(response.getStatus()==HttpStatus.NOT_FOUND.value())
            errorMessage="We have some problems with finding a page";
        else if(response.getStatus()==HttpStatus.FORBIDDEN.value())
            errorMessage="You do not permissions to access this page";
        else if(response.getStatus()==HttpStatus.INTERNAL_SERVER_ERROR.value())
            errorMessage="The server encountered an unexpected condition that prevented the request from being performed";
        else
            errorMessage="There was an unpredictable error";
        model.addAttribute("errorMessage",errorMessage);
        return "errorPage";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
