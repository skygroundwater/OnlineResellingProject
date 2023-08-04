package com.example.onlineresellingproject.dto.mappers;

import com.example.onlineresellingproject.dto.ad.AdDTO;
import com.example.onlineresellingproject.entity.AdEntity;

public interface AdMapper<A extends AdDTO> extends Mapper<AdEntity, A> {

}
