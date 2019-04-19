package com.site.news.demo.repository;

import com.site.news.demo.domain.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NewsItemRepo extends JpaRepository<NewsItem,Long> {

}
