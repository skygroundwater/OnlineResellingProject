package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.service.AdService;
import com.example.onlineresellingproject.service.CommentService;
import com.example.onlineresellingproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контролле для работы с объявлениями
 */
@Tag(name = "Объявления", description = "Контроллер для манипуляции с объявлениями")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    private final UserService userService;

    private final CommentService commentService;

    @Operation(
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ads.class))
                            )
                    )
            },
            tags = "Объявления"
    )
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAds());
    }

    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Ad> addAd(@RequestPart CreateOrUpdateAd properties,
                                    @RequestPart MultipartFile image) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(
                adService.create(
                        userService.findUserEntityByLogin(userDetails.getUsername()),
                        properties, image)
        );
    }

    @Operation(
            summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Добавлена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAd.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    )
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Long id) {
        ExtendedAd ad = adService.getExtendedAd(id);
        return ResponseEntity.ok(
                ad
        );
    }

    @Operation(
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Удалена",
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
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }

    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HttpStatus> removeAd(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        adService.deleteAd(userService.findUserEntityByLogin(userDetails.getUsername()), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Обновление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Объявление обновлено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }
    )
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Ad> updateAds(@PathVariable Long id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(
                adService.updateAd(id, createOrUpdateAd)
        );
    }

    @Operation(
            summary = "Получение объявлений пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Объявление обновлено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ads.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }
    )
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Ads> getAdsMe() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(
                adService.findUserAds(
                        userService.findUserEntityByLogin(userDetails.getUsername()))
        );
    }

    @Operation(
            summary = "Обновление фотографии пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Фото обновлено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Объявление не найдено"
                    )
            }
    )
    // TODO: 15.08.2023 зарефакторить метод, перенести реализацию в сервис объявлений
    @PatchMapping("/{id}/image")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Ad> updateImage(@PathVariable Long id,
                                          @RequestParam MultipartFile multipartFile) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(adService.updateImage(id, userService.findUserEntityByLogin(userDetails.getUsername()), multipartFile));

    }

    @Operation(
            summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Комментарии получены",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Comments.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Коментарии не найдены"
                    )
            }
    )
    @GetMapping("/{id}/comments")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Comments> getComments(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentService.findCommentsByAdId(id)
        );
    }

    @Operation(
            summary = "Добавление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Комментарий получен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Comment.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Коментарий не найден"
                    )
            }
    )
    @PostMapping("/{id}/comments")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Comment> addComment(@PathVariable Long id,
                                              @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(
                commentService.create(
                        userService.findUserEntityByLogin(userDetails.getUsername()),
                        adService.get(id), createOrUpdateComment)
        );
    }

    @Operation(
            summary = "Удаление комментария объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Комментарий удалён",
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
                            responseCode = "404",
                            description = "Коментарий не найден"
                    )
            }
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long adId,
                                                    @PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "обновление комментария объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Комментарий обновлён",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Comment.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Нет авторизации"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Коментарий не найден"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Недопустимый запрос"
                    )
            }
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Comment> updateComment(@PathVariable Long adId,
                                                 @PathVariable Long commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(
                commentService.update(commentId, createOrUpdateComment)
        );
    }
}