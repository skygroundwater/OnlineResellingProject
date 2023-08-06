package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.entity.ProjectEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateImageService<M extends ProjectEntity> {

    M updateImage(Long id, MultipartFile multipartFile);

    default M processImage(Long id, M entity,MultipartFile multipartFile, String path){

        return entity;
    }

}
