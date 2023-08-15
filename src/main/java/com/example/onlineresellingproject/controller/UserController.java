package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.user.NewPassword;
import com.example.onlineresellingproject.dto.user.UpdateUser;
import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Обновление пароля пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Пароль обновлён",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    )
            }
    )
    @PostMapping("/set_password")
    public ResponseEntity<HttpStatus> setPassword(@AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestBody NewPassword newPassword) {
        userService.updateUserPassword(newPassword, userDetails);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Получение получение информации о пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Информация о пользователе получена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                userService.getUser(userDetails));
    }

    @Operation(
            summary = "Обновление информации о пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Информация о пользователе обновлена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UpdateUser.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    )
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(
                userService.createOrUpdate(
                        userDetails, updateUser));
    }

    @Operation(
            summary = "Обновление аватара пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Аватар обновлён",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = HttpStatus.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    )
            }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> updateUserImage(@AuthenticationPrincipal UserDetails userDetails,
                                                      @RequestParam MultipartFile image) {
        userService.updateImage(userDetails, image);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}