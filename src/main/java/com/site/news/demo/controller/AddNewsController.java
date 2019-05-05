package com.site.news.demo.controller;

import com.site.news.demo.domain.NewsItem;
import com.site.news.demo.repository.NewsItemRepository;
import com.site.news.demo.service.FileServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AddNewsController {

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/addnews")
    public String addNews(NewsItem newsItem){
        return "addnews";
    }

    @Autowired
    private FileServiceImplement fileService;

    @PostMapping("/addnews")
    public String addNewNews( @RequestParam("file") MultipartFile image,@Valid NewsItem newsItem,
                             BindingResult bindingResult
    ) throws IOException {
        if(bindingResult.hasErrors())return "addnews";
        else {
            newsItem.setImage(fileService.uploadFile(image));
            newsItem.setDate(new Date());
            newsItemRepository.save(newsItem);
            return "redirect:/";
        }
    }
}
