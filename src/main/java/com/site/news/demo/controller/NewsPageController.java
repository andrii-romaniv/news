package com.site.news.demo.controller;

import com.site.news.demo.domain.Comment;
import com.site.news.demo.domain.NewsItem;
import com.site.news.demo.domain.User;
import com.site.news.demo.repository.CommentRepo;
import com.site.news.demo.repository.NewsItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/news")
public class NewsPageController {

    @Autowired
    NewsItemRepo newsItemRepo;

    @Autowired
    CommentRepo commentRepo;

    @GetMapping("{title}")
    public String newsPage(@PathVariable String title, Model model){
        model.addAttribute("newsItem",newsItemRepo.findByTitle(title));
        return "newsPage";
    }

    @PostMapping
    public String addComment(@AuthenticationPrincipal User user, Comment comment,@RequestParam Long id){
        comment.setAuthor(user);
        comment.setNews(newsItemRepo.findOne(id));
        comment.setData(new Date());
        commentRepo.save(comment);
        return "redirect:/";
    }
}
