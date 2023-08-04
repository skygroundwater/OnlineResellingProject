package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.NewPassword;
import com.example.onlineresellingproject.dto.UpdateUser;
import com.example.onlineresellingproject.dto.User;
import com.example.onlineresellingproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/set_password")
    public ResponseEntity<HttpStatus> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(User.builder()
                .id(1)
                .login("anylogin")
                .phone("justPhone")
                .email("developer@skypro.ru")
                .firstName("myName")
                .image("justImage")
                .build());
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(UpdateUser
                .builder()
                .phone("string")
                .lastName("string")
                .firstName("string")
                .build());
    }

    @PatchMapping("/me/image")
    public ResponseEntity<HttpStatus> updateUserImage(@RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok().build();
    }

}
