package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdService {

    AdEntity post(AdEntity model);

    AdEntity patch(AdEntity model);

    void delete(Long id);

    AdEntity get(Long id);

    Ads getAds();

    Ad createOrUpdate(UserDetails userDetails,
                      CreateOrUpdateAd dto,
                      MultipartFile multipartFile);

    AdEntity findAdEntityByTitle(String title);

    List<AdEntity> findAllAdsByUser(UserEntity userEntity);

    Ads findUsersAds(UserDetails userDetails);
}
