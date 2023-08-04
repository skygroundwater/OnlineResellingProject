package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.repository.AdEntityRepo;
import com.example.onlineresellingproject.service.AdService;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl extends AbstractServiceImpl<AdEntity, Long, AdEntityRepo> implements AdService {
    AdServiceImpl(AdEntityRepo repository) {
        super(repository);
    }
}
