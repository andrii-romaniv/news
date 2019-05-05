package com.site.news.demo.controller;

import com.site.news.demo.repository.NewsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @Autowired
    private NewsItemRepository newsItemRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("newsItem", newsItemRepository.findAllByOrderByIdDesc());
        return "main";
    }

}
