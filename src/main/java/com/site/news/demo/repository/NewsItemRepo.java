package com.site.news.demo.repository;

import com.site.news.demo.domain.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsItemRepo extends JpaRepository<NewsItem,Long> {
   NewsItem findByTitle(String title);
}
