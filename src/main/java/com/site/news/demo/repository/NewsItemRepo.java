package com.site.news.demo.repository;

import com.site.news.demo.domain.NewsItem;
import org.springframework.data.repository.CrudRepository;

public interface NewsItemRepo extends CrudRepository<NewsItem,Long> {

}
