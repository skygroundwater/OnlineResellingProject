package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.entity.AdEntity;

public interface AdService extends OnlineResellingProjectService<AdEntity, Long, CreateOrUpdateAd>, UpdateImageService<AdEntity> {

}
