package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.entity.UserWrapper;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.repository.UserEntityRepo;
import com.example.onlineresellingproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl extends AbstractService<UserEntity, Long, UserEntityRepo, User> implements UserService {
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

    @Override
    public UserEntity findUserEntityByLogin(String username) {
        return getRepository().findUserEntityByUsername(username).orElseThrow(NotFoundInDataBaseException::new);
    }

    @Override
    public boolean userExists(String username) {
        return getRepository().existsUserEntityByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserWrapper(findUserEntityByLogin(username));
    }
}
