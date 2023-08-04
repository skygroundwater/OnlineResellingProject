package com.example.onlineresellingproject.dto.mappers;

import com.example.onlineresellingproject.dto.DTO;
import com.example.onlineresellingproject.entity.OnlineResellingProjectAbstractModel;

public interface Mapper<M extends OnlineResellingProjectAbstractModel, D extends DTO<M>> {

    M map(D dto, M entity);

}
