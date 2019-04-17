package com.site.news.demo.controller;

import com.site.news.demo.domain.NewsItem;
import com.site.news.demo.repository.NewsItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping
    public String addNewNewsItem(@RequestParam String title,@RequestParam String tag,@RequestParam String content,
                                 Map<String,Object> model){
        NewsItem newsItem = new NewsItem(title,tag,content);
        newsItemRepo.save(newsItem);
        Iterable<NewsItem>newsItems=newsItemRepo.findAll();
        model.put("newsItem",newsItems);
        return "main";
    }
}
