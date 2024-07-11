package com.blog.student.StudentData.service;

import com.blog.student.StudentData.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String uploadFile(MultipartFile file);
    byte[] downloadFile(String name) throws IOException;
}
