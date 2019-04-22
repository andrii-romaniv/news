package com.site.news.demo.repository;

import com.site.news.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameStartingWith(String username);
}
