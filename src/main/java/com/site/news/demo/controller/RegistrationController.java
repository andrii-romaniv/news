package com.site.news.demo.controller;

import com.site.news.demo.domain.User;
import com.site.news.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(User user){
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@RequestParam("file") MultipartFile image,
                             @Valid User user, BindingResult bindingResult
    ) throws IOException {
        if(bindingResult.hasErrors())return "registration";
        else
        if(userService.addUser(user,image)!=null)return "redirect:/login";
        else return "registration";
    }
}
