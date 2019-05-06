package com.site.news.demo.controller;

import com.site.news.demo.domain.User;
import com.site.news.demo.repository.UserRepository;
import com.site.news.demo.service.FileServiceImplement;
import com.site.news.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/settings")
@PreAuthorize("#id == principal.id")
public class EditProfileController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileServiceImplement fileService;

    @Autowired
    UserService userService;


    @GetMapping("{id}")
    public String editProfile (@PathVariable long id,User user ,Model model){
        user=userService.getUserObjectById(id).get();
        model.addAttribute("user", user);
        return "editProfilePage";
    }

    @PostMapping("{id}")
    public String loadImage(@PathVariable long id,@RequestParam("file") MultipartFile image,
                            HttpServletRequest request) throws IOException {
        Optional<User> user=userService.getUserObjectById(id);
        user.get().setImage(fileService.uploadFile(image));
        userRepository.save(user.get());
        return "redirect:" + request.getHeader("referer");
    }

    @PostMapping("/update")
    public String updateProfile(@RequestParam long id,@Valid User user,HttpServletRequest request){
        userService.updateUser(id,user);
        HttpSession httpSession=request.getSession();
        httpSession.invalidate();
        return "redirect:/login";
    }


}
