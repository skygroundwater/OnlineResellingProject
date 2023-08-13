package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.mappers.AdMapper;
import com.example.onlineresellingproject.mappers.CommentMapper;
import com.example.onlineresellingproject.service.CommentService;
import com.example.onlineresellingproject.service.FilesService;
import com.example.onlineresellingproject.service.UserService;
import com.example.onlineresellingproject.service.impl.AdServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/ads")
public class AdController {

    private final Logger logger = LoggerFactory.getLogger(AdController.class);

    private final AdMapper adMapper;

    private final CommentMapper commentMapper;

    private final AdServiceImpl adService;

    private final UserService userService;

    private final CommentService commentService;

    private final FilesService filesService;

    public AdController(AdMapper adMapper,
                        CommentMapper commentMapper,
                        AdServiceImpl adService,
                        UserService userService,
                        CommentService commentService, FilesService filesService) {
        this.adMapper = adMapper;
        this.commentMapper = commentMapper;
        this.adService = adService;
        this.commentService = commentService;
        this.userService = userService;
        this.filesService = filesService;
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds(@AuthenticationPrincipal UserDetails userDetails) {
        logger.info("All ads upload method invoke");
        return ResponseEntity.ok(adService.getAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestPart CreateOrUpdateAd properties,
                                    @RequestPart MultipartFile image) {

        return ResponseEntity.ok(adService.createOrUpdate(userDetails, properties, image));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@AuthenticationPrincipal UserDetails userDetails,
                                            @PathVariable Long id) {
        ExtendedAd extendedAd = adMapper.mapToExtendedAd(adService.get(id));
        logger.info("Get ExtendedAd by ID method invoke");

        return ResponseEntity.ok(extendedAd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeAd(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable Long id) {
        adService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CreateOrUpdateAd> updateAd(@AuthenticationPrincipal UserDetails userDetails,
                                                     @PathVariable Long id,
                                                     @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(CreateOrUpdateAd.builder().build());
    }

    // TODO: 06.08.2023 Как и откуда взять айдишник юзера?
    //  Полагаю, что это можно сделать от Spring Security
    //  при выполнении запроса, который требует аутентификации
    //  от самого юзера
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(@AuthenticationPrincipal UserDetails userDetails) {

        ResponseEntity<Ads> ok = ResponseEntity.ok(adService.findUsersAds(userDetails));
        logger.info("User ads upload method invoke");

        return ok;
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Ad> updateImage(@AuthenticationPrincipal UserDetails userDetails,
                                          @PathVariable Long id,
                                          @RequestParam MultipartFile multipartFile) {

        String newFileName = filesService.getNewFileName(multipartFile);
        filesService.saveUserImage(multipartFile, newFileName);

        logger.info("Ads image update method invoke");
        return ResponseEntity.ok(adMapper.mapToAd(adService.updateImage(id, userDetails, multipartFile)));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@AuthenticationPrincipal UserDetails userDetails,
                                                @PathVariable Long id) {

        Comments comments = commentMapper.mapToComments(commentService.findCommentsByAdId(id));
        logger.info("Get comments by Ad Id method invoke");

        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable Long id,
                                              @RequestBody Comment comment) {

        CommentEntity commentEntity = commentMapper.mapToEntity(comment, adService.get(id));
        logger.info("Post comments by Ad Id method invoke");

        return ResponseEntity.ok(commentMapper.mapToComment(commentService.post(commentEntity)));
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(@AuthenticationPrincipal UserDetails userDetails,
                                                    @PathVariable Long id,
                                                    @PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/comments/{commentId}")
    public ResponseEntity<CreateOrUpdateComment> updateComment(@AuthenticationPrincipal UserDetails userDetails,
                                                               @PathVariable Long id,
                                                               @PathVariable Long commentId,
                                                               @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(commentService.createOrUpdate(commentId, createOrUpdateComment));
    }

}
