package com.site.news.demo.controller;

import com.site.news.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersPanelController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public String usersPanel(Model model){
        model.addAttribute("users",userRepo.findAll());
        return "usersPanel";
    }


}
