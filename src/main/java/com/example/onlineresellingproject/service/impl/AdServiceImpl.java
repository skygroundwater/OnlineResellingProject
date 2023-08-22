package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.dto.user.Role;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.exceptions.NoAccessException;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.exceptions.NotValidDataException;
import com.example.onlineresellingproject.exceptions.NotValidModelException;
import com.example.onlineresellingproject.mappers.AdMapper;
import com.example.onlineresellingproject.repository.AdEntityRepo;
import com.example.onlineresellingproject.service.AdService;
import com.example.onlineresellingproject.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdEntityRepo repository;

    private final AdMapper adMapper;

    private final FilesService filesService;

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
                .results(entities.stream()
                        .map(adMapper::mapToAd)
                        .collect(Collectors.toList()))
                .count(entities.size()).build();
    }

    @Override
    public Ad create(UserEntity userEntity,
                     CreateOrUpdateAd createOrUpdateAd,
                     MultipartFile multipartFile) {
        return adMapper.mapToAd(
                post(new AdEntity()
                        .setFieldsAndReturnEntity(userEntity, createOrUpdateAd,
                                filesService.saveAdsImage(multipartFile))));
    }

    @Override
    public void deleteAd(UserEntity userEntity, Long id) {
        if (userEntity.getRole().equals(Role.ADMIN)) {
            delete(id);
        } else {
            if (get(id).getUser().getId().equals(userEntity.getId())) {
                delete(id);
            } else throw new NoAccessException("Вы не можете удалить объявление другого пользователя");
        }
    }

    @Override
    public ExtendedAd getExtendedAd(Long id) {
        return adMapper.mapToExtendedAd(get(id));
    }

    @Override
    public Ad updateAd(Long id, CreateOrUpdateAd createOrUpdateAd) {
        return adMapper.mapToAd(
                patch(get(id)
                        .setFieldsAndReturnEntity(createOrUpdateAd)));
    }

    @Override
    public Ad updateImage(Long id, UserDetails userDetails, MultipartFile multipartFile) {
        AdEntity adEntity = get(id);
        adEntity.setImage(filesService.saveAdsImage(multipartFile));
        return adMapper.mapToAd(adEntity);
    }

    @Override
    public Ads findUserAds(UserEntity userEntity) {
        List<AdEntity> adEntities = repository.findAdEntitiesByUser(userEntity);
        return adMapper.mapToAds(adEntities);
    }
}
