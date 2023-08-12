package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.dto.user.NewPassword;
import com.example.onlineresellingproject.dto.user.UpdateUser;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.entity.UserWrapper;
import com.example.onlineresellingproject.exceptions.NotFoundInDataBaseException;
import com.example.onlineresellingproject.exceptions.NotValidModelException;
import com.example.onlineresellingproject.mappers.UserMapper;
import com.example.onlineresellingproject.repository.UserEntityRepo;
import com.example.onlineresellingproject.service.FilesService;
import com.example.onlineresellingproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserMapper userMapper;

    private final FilesService filesService;

    private final UserEntityRepo repository;

    private final ModelMapper modelMapper;

    @Override
    public final UserEntity post(UserEntity model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    @Override
    public final UserEntity patch(UserEntity model) {
        if (model != null) {
            return repository.save(model);
        } else throw new NotValidModelException();
    }

    @Override
    public UpdateUser createOrUpdate(UserDetails userDetails, UpdateUser updateUser) {
        UserEntity userEntity = findUserEntityByLogin(userDetails.getUsername());
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        patch(userEntity);
        return updateUser;
    }

    @Override
    public UserEntity updateImage(UserDetails userDetails, MultipartFile multipartFile) {
        UserEntity userEntity = findUserEntityByLogin(userDetails.getUsername());
        String newFileName = filesService.getNewFileName(multipartFile);
        userEntity.setImage(filesService.saveUserImage(multipartFile, newFileName));
        return patch(userEntity);
    }

    @Override
    public UserEntity findUserEntityByLogin(String username) {
        return repository.findUserEntityByUsername(username)
                .orElseThrow(NotFoundInDataBaseException::new);
    }

    @Override
    public boolean userExists(String username) {
        return repository.existsUserEntityByUsername(username);
    }

    @Override
    public UserEntity updateUserPassword(NewPassword newPassword, UserDetails userDetails) {
        UserEntity userEntity = findUserEntityByLogin(userDetails.getUsername());
        userEntity.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        post(userEntity);
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserWrapper(findUserEntityByLogin(username));
    }
}
