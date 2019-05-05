package com.site.news.demo.controller;

import com.site.news.demo.domain.Authority;
import com.site.news.demo.domain.User;
import com.site.news.demo.exception.UserNotFoundException;
import com.site.news.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/edituser")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class EditUserController {
@Autowired
UserRepository userRepository;

    @GetMapping("{id}")
    public String editUser(@PathVariable long id,Model model){
        Optional<User> user= Optional.ofNullable(userRepository.findOne(id));
        if(!user.isPresent())throw new UserNotFoundException(id);
        model.addAttribute("user", userRepository.findOne(id));
        model.addAttribute("roles", Authority.values());
        return "editUser";
    }

    @PostMapping
    public String addNewAuthority(@RequestParam long id, Authority authority){
        User user= userRepository.findOne(id);
        user.setAuthority(authority);
        userRepository.save(user);
        return "redirect:/users";
    }

}
