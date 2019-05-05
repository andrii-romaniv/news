package com.site.news.demo.controller;

import com.site.news.demo.domain.User;
import com.site.news.demo.exception.UserNotFoundException;
import com.site.news.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/settings")
public class editProfileController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("{id}")
    @PreAuthorize("#id == principal.id")
    public String editProfile (@PathVariable long id, Model model){
        Optional<User> user= Optional.ofNullable(userRepository.findOne(id));
        if(!user.isPresent())throw new UserNotFoundException(id);
        model.addAttribute("user", userRepository.findOne(id));
        return "editProfilePage";
    }
}
