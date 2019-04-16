package com.site.news.demo.controller;

import com.site.news.demo.domain.NewsItem;
import com.site.news.demo.repository.NewsItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sun.plugin2.message.Message;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private NewsItemRepo newsItemRepo;

    @GetMapping("/")
    public String main(Map<String,Object> model){
        Iterable<NewsItem>newsItems=newsItemRepo.findAll();
        model.put("newsItem",newsItems);
        return "main";
    }
    
}
