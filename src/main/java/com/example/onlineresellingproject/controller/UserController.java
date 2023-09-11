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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

/**
 * Класс-контроллер для обработки запросов, связанных с пользователями.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Метод для обновления пароля пользователя.
     *
     * @param newPassword Новый пароль пользователя.
     * @return Ответ с кодом состояния HTTP 201 (Created) после успешного обновления пароля.
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized).
     * Если запрос недопустим, вернется код состояния HTTP 403 (Forbidden).
     */
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HttpStatus> setPassword(@RequestBody NewPassword newPassword) {
        userService.updateUserPassword(newPassword, (UserDetails)
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Метод для получения информации о текущем пользователе.
     *
     * @return Ответ с информацией о пользователе и кодом состояния HTTP 201 (Created).
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized).
     */
    @Operation(
            summary = "Получение информации о пользователе",
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(
                userService.getUser((UserDetails)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal()));
    }

    /**
     * Метод для обновления информации о текущем пользователе.
     *
     * @param updateUser Обновленные данные пользователя.
     * @return Ответ с обновленной информацией о пользователе и кодом состояния HTTP 201 (Created).
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized).
     */
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(
                userService.createOrUpdate(
                        (UserDetails)
                                SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                                        .getPrincipal(), updateUser));
    }

    /**
     * Метод для обновления аватара текущего пользователя.
     *
     * @param image Файл с новым аватаром пользователя.
     * @return Ответ с кодом состояния HTTP 201 (Created) после успешного обновления аватара.
     * Если пользователь не авторизован, вернется код состояния HTTP 401 (Unauthorized).
     */
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HttpStatus> updateUserImage(@RequestParam MultipartFile image) {
        userService.updateImage(
                (UserDetails)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal(),
                image);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}