package com.site.news.demo.repository;

import com.site.news.demo.domain.Comment;
import com.site.news.demo.domain.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findAllByNewsOrderByIdDesc(NewsItem newsItem);
}
