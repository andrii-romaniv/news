package com.site.news.demo.controller;

import com.site.news.demo.domain.Authority;
import com.site.news.demo.domain.User;
import com.site.news.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String save(){
        return "redirect:/usersPanel";
    }
}
