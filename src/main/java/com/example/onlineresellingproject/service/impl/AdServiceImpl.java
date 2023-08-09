package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.repository.AdEntityRepo;
import com.example.onlineresellingproject.service.AdService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdServiceImpl extends AbstractService<AdEntity, Long, AdEntityRepo, CreateOrUpdateAd> implements AdService {
    AdServiceImpl(AdEntityRepo repository,
                  ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public CreateOrUpdateAd createOrUpdate(Long key, CreateOrUpdateAd dto) {
        AdEntity adEntity = get(key);
        adEntity.setTitle(dto.getTitle());
        adEntity.setPrice(dto.getPrice());
        adEntity.setDescription(dto.getDescription());
        post(adEntity);
        return dto;
    }

    @Override
    public AdEntity updateImage(Long id, MultipartFile multipartFile) {
        AdEntity adEntity = get(id);
        return processImage(id, adEntity, multipartFile, "path/to/file/holder");
    }

    @Override
    public AdEntity findAdEntityByTitle(String title) {
        return getRepository().findAdEntityByTitle(title)
                .orElseThrow(() -> new NotFoundInDataBaseException("Объявление по названию не найдено"));
    }
}
