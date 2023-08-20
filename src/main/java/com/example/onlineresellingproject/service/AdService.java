package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public interface AdService {

    AdEntity post(AdEntity model);

    AdEntity patch(AdEntity model);

    void delete(Long id);

    AdEntity get(Long id);

    Ads getAds();

    Ad create(UserEntity userEntity,
              CreateOrUpdateAd dto,
              MultipartFile multipartFile);

    Ad updateImage(Long id, UserDetails userDetails, MultipartFile multipartFile);

    Ads findUserAds(UserEntity userEntity);

    void deleteAd(UserEntity userEntity, Long id);

    ExtendedAd getExtendedAd(Long id);

    Ad updateAd(Long id, CreateOrUpdateAd createOrUpdateAd);
}
