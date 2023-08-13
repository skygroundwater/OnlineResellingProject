package com.example.onlineresellingproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {

    @Value("${path.to.image}")
    private String imagesPath;
    @Value("${path.to.image.users}")
    private String usersImagesPath;

    @GetMapping("/images/users/{imageId}")
    public byte[] getUserImage(@PathVariable String imageId) throws IOException {
        String filePathInStorage = usersImagesPath + File.separator + imageId;
        File newFile = new File(imagesPath + filePathInStorage);
        return Files.readAllBytes(newFile.toPath());
    }



}