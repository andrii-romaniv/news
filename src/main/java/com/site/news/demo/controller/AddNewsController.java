package com.site.news.demo.controller;

import com.site.news.demo.domain.NewsItem;
import com.site.news.demo.repository.NewsItemRepository;
import com.site.news.demo.service.FileServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AddNewsController {

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/addnews")
    public String addNews(){
        return "addnews";
    }

    @Autowired
    private FileServiceImplement fileService;

    @PostMapping("/addnews")
    public String addNewNews(NewsItem newsItem,@RequestParam("file") MultipartFile image
    ) throws IOException {
        newsItem.setImage(fileService.uploadFile(image));
        newsItem.setDate(new Date());
        newsItemRepository.save(newsItem);
        return "redirect:/";
    }
}
