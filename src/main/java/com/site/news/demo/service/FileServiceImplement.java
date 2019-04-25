package com.site.news.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImplement implements FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public String uploadFile(MultipartFile image) throws IOException {
        File uploadDirectory=new File(uploadPath);
        if(!uploadDirectory.exists())uploadDirectory.mkdir();
        String genereteName= UUID.randomUUID().toString();
        String resultFilename=genereteName+"-"+image.getOriginalFilename();
        image.transferTo(new File(uploadPath+"/"+resultFilename));
        return resultFilename;
    }
}
