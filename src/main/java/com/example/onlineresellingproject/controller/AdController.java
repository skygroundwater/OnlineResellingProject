package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.service.CommentService;
import com.example.onlineresellingproject.service.UserService;
import com.example.onlineresellingproject.mappers.AdMapper;
import com.example.onlineresellingproject.mappers.CommentMapper;
import com.example.onlineresellingproject.service.impl.AdServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/ads")
public class AdController {

    private final AdMapper adMapper;

    private final CommentMapper commentMapper;

    private final AdServiceImpl adService;

    private final UserService userService;

    private final CommentService commentService;

    public AdController(AdMapper adMapper,
                        CommentMapper commentMapper,
                        AdServiceImpl adService,
                        UserService userService,
                        CommentService commentService) {
        this.adMapper = adMapper;
        this.commentMapper = commentMapper;
        this.adService = adService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAds());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestBody CreateOrUpdateAd ad,
                                    @RequestParam MultipartFile multipartFile) {

        return ResponseEntity.ok(adService.createOrUpdate(userDetails, ad, multipartFile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(
                adMapper.mapToExtendedAd(
                        adService.get(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeAd(@PathVariable Long id) {
        adService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CreateOrUpdateAd> updateAd(@PathVariable Long id,
                                                     @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(CreateOrUpdateAd.builder().build());
    }

    // TODO: 06.08.2023 Как и откуда взять айдишник юзера?
    //  Полагаю, что это можно сделать от Spring Security
    //  при выполнении запроса, который требует аутентификации
    //  от самого юзера
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(adService.findUsersAds(userDetails));
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Ad> updateImage(@PathVariable Long id,
                                          @AuthenticationPrincipal UserDetails userDetails,
                                          @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok(adMapper.mapToAd(adService.updateImage(id, userDetails, multipartFile)));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.mapToComments(
                        commentService.findCommentsByAdId(id)));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id,
                                              @RequestBody Comment comment) {

        return ResponseEntity.ok(commentMapper.mapToComment(
                commentService.post(
                        commentMapper.mapToEntity(comment, adService.get(id)))));
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id,
                                                    @PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/comments/{commentId}")
    public ResponseEntity<CreateOrUpdateComment> updateComment(@PathVariable Long id,
                                                               @PathVariable Long commentId,
                                                               @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok(commentService.createOrUpdate(commentId, createOrUpdateComment));
    }

}
