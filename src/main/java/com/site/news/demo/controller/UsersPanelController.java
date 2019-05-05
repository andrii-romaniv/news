package com.site.news.demo.controller;

import com.site.news.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UsersPanelController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String usersPanel(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "usersPanel";
    }

    @PostMapping("find")
    public String findUser(@RequestParam String find,Model model){
        if(!StringUtils.isEmpty(find))model
                .addAttribute("users", userRepository.findByUsernameStartingWith(find));
        else
            model.addAttribute("users", userRepository.findAll());
        return "usersPanel";
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable long id){
        userRepository.delete(id);
        if(userRepository.count()!=0)return "redirect:/users";
        else return "redirect:/";
    }
}
