package com.site.news.demo.controller;

import com.site.news.demo.domain.User;
import com.site.news.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, @RequestParam("file") MultipartFile image) throws IOException {
        if(userService.addUser(user,image))return "redirect:/login";
        else return "registration";
    }

}
