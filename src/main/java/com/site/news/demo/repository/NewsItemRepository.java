package com.site.news.demo.repository;

import com.site.news.demo.domain.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsItemRepository extends JpaRepository<NewsItem,Long> {
   List<NewsItem> findAllByOrderByIdDesc();
   NewsItem findByTitle(String title);
}
