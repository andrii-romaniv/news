package com.site.news.demo.service;

import com.site.news.demo.domain.Authority;
import com.site.news.demo.domain.User;
import com.site.news.demo.exception.UserNotFoundException;
import com.site.news.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FileServiceImplement fileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User addUser(User user,MultipartFile image) throws IOException {
        if(user==null )return null;
        else
            if(userRepository.findByUsername(user.getUsername())!=null)return null;
                else{
            if(userRepository.count()==0)user.setAuthority(Authority.ROLE_ADMIN);
            else user.setAuthority(Authority.ROLE_USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setImage(fileService.uploadFile(image));
            userRepository.save(user);
            return user;
        }
    }

    public User updateUser(long id,User user){
        User editedUser=getUserObjectById(id).get();
        if(StringUtils.isEmpty(user.getImage()))user.setImage(editedUser.getImage());
        if(StringUtils.isEmpty(user.getAuthority()))user.setAuthority(editedUser.getAuthority());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public void updateProfileImage(long id,MultipartFile image) throws IOException {
        Optional<User> user=getUserObjectById(id);
        user.get().setImage(fileService.uploadFile(image));
        userRepository.save(user.get());
    }

    public Optional<User> getUserObjectById(long id){
        Optional<User> user= Optional.ofNullable(userRepository.findOne(id));
        if(!user.isPresent())throw new UserNotFoundException(id);
        return user;
    }

    public User getActiveUser(){
        return  (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
