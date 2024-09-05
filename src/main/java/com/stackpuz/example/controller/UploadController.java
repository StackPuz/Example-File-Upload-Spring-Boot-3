package com.stackpuz.example.controller;

import com.stackpuz.example.entity.Product;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @PostMapping("/submit")
    public Map<String, String> submit(@RequestParam MultipartFile image, Product product) throws IOException {
        String uploadPath = "src/main/resources/static/uploads/";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String filename = image.getOriginalFilename();
        Path filePath = Paths.get(uploadPath + filename);
        Files.write(filePath, image.getBytes());
        Map<String, String> response = new HashMap<>();
        response.put("name", product.getName());
        response.put("image", filename);
        return response;
    }

}