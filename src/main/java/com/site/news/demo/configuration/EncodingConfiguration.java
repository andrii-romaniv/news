package com.site.news.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncodingConfiguration {
    @Bean
    public PasswordEncoder getpasswordEncoder(){
        return new BCryptPasswordEncoder(6);
    }

}
