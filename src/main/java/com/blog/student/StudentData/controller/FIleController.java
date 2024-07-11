package com.blog.student.StudentData.controller;

import com.blog.student.StudentData.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FIleController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        String response = imageService.uploadFile(file);
        if(response.contains("successfully")){
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @GetMapping("/download-file/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] data = imageService.downloadFile(fileName);
        if(data == null){
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        String type;
        if(fileName.contains(".pdf")){
            type = "application/pdf";
        }
        else if(fileName.contains(".png")){
            type = "image/png";
        }
        else{
            type = "image/jpeg";
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename(fileName).build());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(type))
                .headers(httpHeaders)
                .body(data);
    }
}
