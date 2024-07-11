package com.blog.student.StudentData.service.impl;

import com.blog.student.StudentData.entity.Image;
import com.blog.student.StudentData.helper.FileEncryptionDecryption;
import com.blog.student.StudentData.repository.ImageRepository;
import com.blog.student.StudentData.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImageServiceImplementation implements ImageService {

    private final String URL = "D:\\Spring Boot Tut\\StudentData\\StudentData\\src\\main\\resources\\static\\images";

    @Autowired
    private ImageRepository imageRepository;

    private final List<String> list = List.of("image/png", "image/jpeg", "application/pdf");
    private final Set<String> supportedFiles = new HashSet<>(list);

    @Override
    public String uploadFile(MultipartFile file) {
        String fileType = file.getContentType();
        System.out.println(fileType);

        if (!supportedFiles.contains(fileType)) {
            return fileType + " is not supported";
        }

        try {
            String originalName = file.getOriginalFilename();
            if (originalName == null) {
                return "Invalid file name";
            }

            // Encode the file name
            String encodedName = Base64.getUrlEncoder().encodeToString(originalName.getBytes());
            System.out.println(encodedName);

            // Extract the file extension
            int idx = originalName.lastIndexOf(".");
            String type = originalName.substring(idx + 1);
            System.out.println(type);

            // Generate secret key for encryption
            SecretKey secretKey = FileEncryptionDecryption.generateKey();
            String key = FileEncryptionDecryption.keyToString(secretKey);

            // Convert MultipartFile to File
            File inputFile = new File(URL, originalName);
            file.transferTo(inputFile);
            System.out.println("File converted to: " + inputFile.getAbsolutePath());

            // Encrypt the file
            File outputFile = new File(URL, encodedName + ".enc");
            FileEncryptionDecryption.encryptFile(secretKey, inputFile, outputFile);
            System.out.println("Encrypted file created at: " + outputFile.getAbsolutePath());

            // Save the metadata to the database
            Image image = new Image();
            image.setEncodedName(encodedName);
            image.setExtension(type);
            image.setSecretKey(key);
            imageRepository.save(image);

            // Delete the original file after encryption
            Files.deleteIfExists(inputFile.toPath());

            return "File uploaded and encrypted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "File upload failed: " + e.getMessage();
        }
    }

    @Override
    public byte[] downloadFile(String name) throws IOException {
        int i = name.lastIndexOf(".");
        String nameWithoutExtension = name.substring(0, i);
        String type = name.substring(i + 1);

        // Encode the file name
        String encoded = Base64.getUrlEncoder().encodeToString(name.getBytes());
        String encodedFilePath = URL + "\\" + encoded + ".enc";
        String outputFilePath = URL + "\\" + encoded + "." + type;

        Image byPath = imageRepository.findByEncodedName(encoded);
        if (byPath == null) {
            return null;
        }

        try {
            // Retrieve the secret key
            String key = byPath.getSecretKey();
            SecretKey secretKey = FileEncryptionDecryption.stringToKey(key);

            // Decrypt the file
            File inputFile = new File(encodedFilePath);
            File outputFile = new File(outputFilePath);
            FileEncryptionDecryption.decryptFile(secretKey, inputFile, outputFile);

            // Read the decrypted file bytes
            byte[] data = Files.readAllBytes(outputFile.toPath());

            // Delete the decrypted file after reading
            Files.deleteIfExists(outputFile.toPath());

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

