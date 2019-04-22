package com.site.news.demo.controller;

import com.site.news.demo.repository.NewsItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsPageController {

    @Autowired
    NewsItemRepo newsItemRepo;

    @GetMapping("{title}")
    public String newsPage(@PathVariable String title, Model model){
        model.addAttribute("newsItem",newsItemRepo.findByTitle(title));
        return "newsPage";
    }
}
