package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.exceptions.NotValidDataException;
import com.example.onlineresellingproject.exceptions.NotValidModelException;
import com.example.onlineresellingproject.mappers.AdMapper;
import com.example.onlineresellingproject.repository.AdEntityRepo;
import com.example.onlineresellingproject.service.AdService;
import com.example.onlineresellingproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {


    private final AdEntityRepo repository;

    private final ModelMapper modelMapper;

    private final AdMapper adMapper;

    private final UserService userService;

    @Override
    public final AdEntity post(AdEntity model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    @Override
    public final AdEntity patch(AdEntity model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    @Override
    public final void delete(Long id) {
        if (id != null) {
            repository.deleteById(id);
        } else throw new NotValidDataException();
    }

    @Override
    public final AdEntity get(Long id) {
        if (id != null) {
            return repository.findById(id).orElseThrow(NotFoundInDataBaseException::new);
        } else throw new NotValidDataException();
    }

    @Override
    public final Ads getAds() {
        List<AdEntity> entities = repository.findAll();
        return Ads.builder()
                .ads(entities.stream()
                        .map(adMapper::mapToAd)
                        .collect(Collectors.toList()))
                .count(entities.size()).build();
    }

    @Override
    public Ad createOrUpdate(UserDetails userDetails,
                             CreateOrUpdateAd dto,
                             MultipartFile multipartFile) {
        UserEntity userEntity = userService.findUserEntityByLogin(userDetails.getUsername());
        AdEntity adEntity = new AdEntity();
        adEntity.setUser(userEntity);
        adEntity.setDescription(dto.getDescription());
        adEntity.setPrice(dto.getPrice());
        adEntity.setTitle(dto.getTitle());
        post(adEntity);
        return adMapper.mapToAd(adEntity);
    }

    @Override
    public AdEntity updateImage(Long id, UserDetails userDetails, MultipartFile multipartFile) {
        AdEntity adEntity = get(id);
        return adEntity;
    }

    @Override
    public AdEntity findAdEntityByTitle(String title) {
        return repository.findAdEntityByTitle(title)
                .orElseThrow(() -> new NotFoundInDataBaseException("Объявление по названию не найдено"));
    }

    @Override
    public List<AdEntity> findAllAdsByUser(UserEntity userEntity) {
        return repository.findAdEntitiesByUser(userEntity);
    }

    @Override
    public Ads findUsersAds(UserDetails userDetails) {
        UserEntity userEntity = userService.findUserEntityByLogin(userDetails.getUsername());
        List<AdEntity> adEntities = findAllAdsByUser(userEntity);
        return adMapper.mapToAds(adEntities);
    }
}
