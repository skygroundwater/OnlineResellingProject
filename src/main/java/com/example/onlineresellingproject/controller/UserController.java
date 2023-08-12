package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.user.NewPassword;
import com.example.onlineresellingproject.dto.user.UpdateUser;
import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.mappers.UserMapper;
import com.example.onlineresellingproject.service.FilesService;
import com.example.onlineresellingproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final FilesService filesService;

    private final UserMapper userMapper;

    public UserController(UserService userService, FilesService filesService, UserMapper userMapper) {
        this.userService = userService;
        this.filesService = filesService;
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

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> updateUserImage(@AuthenticationPrincipal UserDetails userDetails,
                                                      @RequestParam MultipartFile image) {
        String newFileName = filesService.getNewFileName(image);
        filesService.saveUserImage(image, newFileName);

        System.out.println("User image upload method call"); // TODO LOG
        return ResponseEntity.ok().build();
    }
}
