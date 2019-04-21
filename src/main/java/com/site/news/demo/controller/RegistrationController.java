package com.site.news.demo.controller;

import com.site.news.demo.domain.Authority;
import com.site.news.demo.domain.User;
import com.site.news.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, Model model){
        User userFromDB=userRepo.findByUsername(user.getUsername());
        if(userFromDB!=null){
            return "registration";
        }
        else{
            if(userRepo.count()==0)user.setAuthority(Authority.ROLE_ADMIN);
            else user.setAuthority(Authority.ROLE_USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
        return "redirect:/login";
    }
}
