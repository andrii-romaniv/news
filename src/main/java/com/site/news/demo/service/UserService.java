package com.site.news.demo.service;

import com.site.news.demo.domain.Authority;
import com.site.news.demo.domain.User;
import com.site.news.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FileService fileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user,MultipartFile image) throws IOException {
        User userFromDB=userRepo.findByUsername(user.getUsername());
        if(userFromDB!=null){
            return false;
        }
        else{
            if(userRepo.count()==0)user.setAuthority(Authority.ROLE_ADMIN);
            else user.setAuthority(Authority.ROLE_USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setImage(fileService.uploadFile(image));
            userRepo.save(user);
            return true;
        }
    }
}
