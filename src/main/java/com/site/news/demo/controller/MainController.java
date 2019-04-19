package com.site.news.demo.controller;

import com.site.news.demo.domain.NewsItem;
import com.site.news.demo.repository.NewsItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private NewsItemRepo newsItemRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String main(Model model){
        Iterable<NewsItem>newsItems=newsItemRepo.findAll(SortById());
        model.addAttribute("newsItem",newsItems);
        return "main";
    }

    private static Sort SortById(){
        return new Sort(Sort.Direction.DESC,"id");
    }


}
