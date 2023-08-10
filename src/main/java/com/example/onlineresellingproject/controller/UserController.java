package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.user.NewPassword;
import com.example.onlineresellingproject.dto.user.UpdateUser;
import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.mappers.UserMapper;
import com.example.onlineresellingproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/set_password")
    public ResponseEntity<HttpStatus> setPassword(@AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestBody NewPassword newPassword) {
        userService.updateUserPassword(newPassword, userDetails);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                userMapper.mapToUser(
                        userService.findUserEntityByLogin(
                                userDetails.getUsername())));
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(
                userService.createOrUpdate(
                        userDetails, updateUser));
    }

    @PatchMapping("/me/image")
    public ResponseEntity<HttpStatus> updateUserImage(@AuthenticationPrincipal UserDetails userDetails,
                                                      @RequestPart MultipartFile multipartFile) {

        return ResponseEntity.ok().build();
    }
}
