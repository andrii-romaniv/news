package com.site.news.demo.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


public interface FileService {
    String uploadFile(MultipartFile image) throws IOException;
}
