package com.site.news.demo.controller;

import com.site.news.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UsersPanelController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public String usersPanel(Model model){
        model.addAttribute("users",userRepo.findAll());
        return "usersPanel";
    }

    @PostMapping("find")
    public String findUser(@RequestParam String find,Model model){
        if(find!=null && !find.isEmpty())model
                .addAttribute("users",userRepo.findByUsernameStartingWith(find));
        else
            model.addAttribute("users",userRepo.findAll());
        return "usersPanel";
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable long id){
        userRepo.delete(id);
        if(userRepo.count()!=0)return "redirect:/users";
        else return "redirect:/";
    }
}
