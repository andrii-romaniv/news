package com.site.news.demo.controller;

import com.site.news.demo.domain.Comment;
import com.site.news.demo.domain.NewsItem;
import com.site.news.demo.repository.CommentRepo;
import com.site.news.demo.repository.NewsItemRepo;
import com.site.news.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/news")
public class NewsPageController {

    @Autowired
    NewsItemRepo newsItemRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserService userService;

    @GetMapping("{title}")
    public String newsPage(@PathVariable String title, Model model){
        NewsItem newsItem=newsItemRepo.findByTitle(title);
        model.addAttribute("newsItem",newsItemRepo.findByTitle(title));
        model.addAttribute("comments",commentRepo.findAllByNewsOrderByIdDesc(newsItem));
        return "newsPage";
    }

    @PostMapping
    public String addComment(Comment comment, @RequestParam Long id, HttpServletRequest request){
        if(comment.getContent().isEmpty()) return "redirect:"+request.getHeader("referer");
        else {
            long commentId = commentRepo.count();
            comment.setId(++commentId);
            comment.setAuthor(userService.getCorrectUser());
            comment.setNews(newsItemRepo.findOne(id));
            comment.setData(new Date());
            commentRepo.save(comment);
            return "redirect:" + request.getHeader("referer");
        }
    }
}
