package com.site.news.demo.controller;

import com.site.news.demo.domain.NewsItem;
import com.site.news.demo.repository.NewsItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AddNewsController {

    @Autowired
    private NewsItemRepo newsItemRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/addnews")
    public String addNews(){
        return "addnews";
    }

    @PostMapping("/addnews")
    public String addNewNews(NewsItem newsItem,
                                 @RequestParam("image_file") MultipartFile image,
                                 Model model
    ) throws IOException {
        ;
        if(image!=null && !image.getOriginalFilename().isEmpty()){
            File uploadDirectory=new File(uploadPath);
            if(!uploadDirectory.exists())uploadDirectory.mkdir();
            String genereteName= UUID.randomUUID().toString();
            String resultFilename=genereteName+"."+image.getOriginalFilename();
            image.transferTo(new File(uploadPath+"/"+resultFilename));
            newsItem.setImage(resultFilename);
        }
        newsItemRepo.save(newsItem);
        model.addAttribute("newsItem",newsItemRepo.findAll());
        return "main";
    }
}
