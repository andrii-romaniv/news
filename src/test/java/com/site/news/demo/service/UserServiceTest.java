package com.site.news.demo.service;


import com.site.news.demo.domain.User;
import com.site.news.demo.exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/users-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/users-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceTest extends Assert {

    @Autowired
    private UserService userService;

    @Test
    public void loadUserByUsername(){
        String userName="User";
        UserDetails findUser=userService.loadUserByUsername(userName);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserWhoDoesNotExistByUsername(){
        String dontExistUserName="TestUser";
        UserDetails findUser=userService.loadUserByUsername(dontExistUserName);
    }

    @Test
    public void addUser() throws IOException {
        String userName="TestUser";
        String password="pass";
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail("test.mail@gmail.com");
        MultipartFile file = getMultipartFile();
        User isUserCreatedOnDb=userService.addUser(user, file);
        assertNotNull(isUserCreatedOnDb);
    }

    @Test
    public void addExistUser()throws IOException{
        String userName="User";
        String password="pass";
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail("test.mail@gmail.com");
        MultipartFile file = getMultipartFile();
        User isUserCreatedOnDb=userService.addUser(user, file);
        assertNull(isUserCreatedOnDb);
    }

    @Test
    public void getUserObjectById(){
        long id=1;
        Optional<User> user= userService.getUserObjectById(id);
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserWhoDoesNotExistObjectById(){
        long id=9;
        Optional<User> user= userService.getUserObjectById(id);
    }

    private MultipartFile getMultipartFile() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
    }
}
