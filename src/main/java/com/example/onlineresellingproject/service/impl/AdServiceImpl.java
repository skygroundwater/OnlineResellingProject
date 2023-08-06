package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.repository.AdEntityRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdServiceImpl extends AbstractService<AdEntity, Long, AdEntityRepo, CreateOrUpdateAd> implements com.example.onlineresellingproject.service.AdService {
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
        return CreateOrUpdateAd.map(post(adEntity), getModelMapper());
    }

    @Override
    public AdEntity updateImage(Long id, MultipartFile multipartFile) {
        AdEntity adEntity = get(id);
        return processImage(id, adEntity, multipartFile, "path/to/file/holder");
    }
}
