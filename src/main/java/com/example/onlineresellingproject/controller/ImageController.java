package com.example.onlineresellingproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final Logger logger = LoggerFactory.getLogger(AdController.class);

    @Value("${path.to.image}")
    private String imagesPath;
    @Value("${path.to.image.users}")
    private String usersImagesPath;
    @Value("${path.to.image.ads}")
    private String adsImagesPath;

    @GetMapping("/images/users/{imageId}")
    public byte[] getUserImage(@AuthenticationPrincipal UserDetails userDetails,
                               @PathVariable String imageId) throws IOException {
        String filePathInStorage = usersImagesPath + File.separator + imageId;
        File newFile = new File(imagesPath + filePathInStorage);

        logger.info("User image download method call");
        return Files.readAllBytes(newFile.toPath());
    }

    @GetMapping("/images/ads/{imageId}")
    public byte[] getAdImage(@AuthenticationPrincipal UserDetails userDetails,
                             @PathVariable String imageId) throws IOException {
        String filePathInStorage = adsImagesPath + File.separator + imageId;
        File newFile = new File(imagesPath + filePathInStorage);

        logger.info("Ads image download method call");
        return Files.readAllBytes(newFile.toPath());
    }

}