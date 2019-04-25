package com.site.news.demo.controller;

import com.site.news.demo.domain.Authority;
import com.site.news.demo.domain.User;
import com.site.news.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/edituser")
public class EditUserController {
@Autowired
UserRepo userRepo;

    @GetMapping("{id}")
    public String editUser(@PathVariable long id,Model model){
        model.addAttribute("user",userRepo.findOne(id));
        model.addAttribute("roles", Authority.values());
        return "editUser";
    }

    @PostMapping
    public String addNewAuthority(@RequestParam long id, Authority authority){
        User user=userRepo.findOne(id);
        user.setAuthority(authority);
        userRepo.save(user);
        return "redirect:/users";
    }

}
