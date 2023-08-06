package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.repository.UserEntityRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl extends AbstractService<UserEntity, Long, UserEntityRepo, User> implements com.example.onlineresellingproject.service.UserService {
    public UserServiceImpl(UserEntityRepo userEntityRepo,
                           ModelMapper modelMapper) {
        super(userEntityRepo, modelMapper);
    }

    @Override
    public User createOrUpdate(Long key, User dto) {
        return null;
    }

    @Override
    public UserEntity updateImage(Long id, MultipartFile multipartFile) {
        UserEntity userEntity = get(id);
        return processImage(id, userEntity, multipartFile, "path/to/file/holder");
    }
}
