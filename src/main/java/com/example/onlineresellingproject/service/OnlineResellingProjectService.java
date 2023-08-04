package com.example.onlineresellingproject.service;

import com.example.onlineresellingproject.entity.OnlineResellingProjectAbstractModel;

public interface OnlineResellingProjectService<M extends OnlineResellingProjectAbstractModel> {

    M post(M m);

    M patch(M m);

    void delete(Long id);

    M get(Long id);

}
