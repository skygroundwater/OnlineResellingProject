package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.service.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {

    private final Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);

    @Value("${path.to.image}")
    private String imagesPath;

    @Value("${path.to.image.users}")
    private String usersImagesPath;

    @Value("${path.to.image.ads}")
    private String adsImagesPath;

    @PostConstruct
    private void init() {
        logger.info("File service init method");
        Path path = Path.of(imagesPath);
        Path path1 = Path.of(imagesPath + usersImagesPath);
        Path path2 = Path.of(imagesPath + adsImagesPath);
        Path path3 = Path.of(imagesPath + "/images");
        try {
            if (Files.notExists(path)) {
                Files.createDirectory(path.toAbsolutePath());
                logger.debug("Create path {}", path.toAbsolutePath());
            }
            if (Files.notExists(path3)) {
                Files.createDirectory(path3.toAbsolutePath());
                logger.debug("Create path {}", path3.toAbsolutePath());
            }
            if (Files.notExists(path1)) {
                Files.createDirectory(path1.toAbsolutePath());
                logger.debug("Create path {}", path1.toAbsolutePath());
            }
            if (Files.notExists(path2)) {
                Files.createDirectory(path2.toAbsolutePath());
                logger.debug("Create path {}", path2.toAbsolutePath());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


    @Override
    public String saveUserImage(MultipartFile file) {
        String filePathInStorage = usersImagesPath + File.separator + getNewFileName(file);
        File newFile = new File(imagesPath + filePathInStorage);
        uploadFile(file, newFile);
        logger.debug("User image saved {}, ", newFile);
        return filePathInStorage;
    }

    @Override
    public String saveAdsImage(MultipartFile file) {
        String filePathInStorage = adsImagesPath + File.separator + getNewFileName(file);
        File newFile = new File(imagesPath + filePathInStorage);
        uploadFile(file, newFile);
        logger.debug("Ad image saved {}, ", newFile);
        return filePathInStorage;
    }

    private String getNewFileName(MultipartFile file) {
        String[] split = Objects.requireNonNull(file.getName()).split("\\.");
        String extension = split[split.length - 1];
        String newFileName = UUID.randomUUID() + "." + extension;
        logger.debug("Get new file name {}", newFileName);
        return newFileName;
    }

    public boolean deleteFile(String path) {
        //TODO delete userImage when update
        return true;
    }

    private void uploadFile(MultipartFile file, File newFile) {
        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
             FileOutputStream fos = new FileOutputStream(newFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            while (bis.read(buffer) > 0) {
                bos.write(buffer);
            }
        } catch (IOException e) {
            logger.error("Error uploading file {} or creating new file {}",
                    file.getOriginalFilename(), newFile.getAbsolutePath());
            throw new RuntimeException(e);
        }
    }
}
