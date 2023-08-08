package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.dto.DTO;
import com.example.onlineresellingproject.entity.ProjectEntity;

import java.util.List;

public interface OnlineResellingProjectService<M extends ProjectEntity, O, D extends DTO> {

    M post(M model);

    M patch(M model);

    void delete(O key);

    M get(O key);

    List<M> findAll();

    D createOrUpdate(O key, D dto);

}
