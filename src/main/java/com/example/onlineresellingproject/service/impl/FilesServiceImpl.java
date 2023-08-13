package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.service.FilesService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.image}")
    private String imagesPath;

    @Value("${path.to.image.users}")
    private String usersImagesPath;

    @Value("${path.to.image.ads}")
    private String adsImagesPath;

    Path path;

    Path path1;

    Path path2;

    @PostConstruct
    private void init() {
        path = Path.of(imagesPath);
        path1 = Path.of(imagesPath + usersImagesPath);
        path2 = Path.of(imagesPath + adsImagesPath);
        try {
            if (Files.notExists(path)) {
                Files.createDirectory(path.toAbsolutePath());
            }
            if (Files.notExists(path1)) {
                Files.createDirectory(path1.toAbsolutePath());
            }
            if (Files.notExists(path2)) {
                Files.createDirectory(path2.toAbsolutePath());
            }
        } catch (IOException e) {
            //todo LOG
            e.printStackTrace();
        }
    }


    @Override
    public String saveUserImage(MultipartFile file, String newFileName) {
        String filePathInStorage = usersImagesPath + File.separator + newFileName;
        File newFile = new File(imagesPath + filePathInStorage);
        uploadFile(file, newFile);
        return filePathInStorage;
    }

    @Override
    public String saveAdsImage(MultipartFile file, String newFileName) {
        String filePathInStorage = adsImagesPath + File.separator + newFileName;
        File newFile = new File(imagesPath + filePathInStorage);
        uploadFile(file, newFile);
        return filePathInStorage;
    }


    @Override
    public String getNewFileName(MultipartFile file) {
        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String extension = split[split.length - 1];
        return UUID.randomUUID() + "." + extension;
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
            //todo LOG
            throw new RuntimeException(e);
        }
    }
}
