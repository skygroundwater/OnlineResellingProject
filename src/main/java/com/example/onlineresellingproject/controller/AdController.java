package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.dto.comment.Comment;
import com.example.onlineresellingproject.dto.comment.Comments;
import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
import com.example.onlineresellingproject.dto.user.User;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.CommentEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.service.AdService;
import com.example.onlineresellingproject.service.CommentService;
import com.example.onlineresellingproject.service.OnlineResellingProjectService;
import com.example.onlineresellingproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {

    private final ModelMapper modelMapper;

    private final AdService adService;

    private final UserService userService;

    private final CommentService commentService;

    public AdController(ModelMapper modelMapper,
                        AdService adService,
                        UserService userService,
                        CommentService commentService) {
        this.modelMapper = modelMapper;
        this.adService = adService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(Ads.map(adService.findAll(), modelMapper));
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestBody Ad ad,
                                    @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok(
                Ad.mapToDTo(adService.post(
                        Ad.mapToEntity(ad, userService)), modelMapper));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(
                ExtendedAd.map(adService.get(id), modelMapper));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeAd(@PathVariable Long id) {
        adService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CreateOrUpdateAd> updateAd(@PathVariable Long id,
                                                     @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(adService.createOrUpdate(id, createOrUpdateAd));
    }

    // TODO: 06.08.2023 Как и откуда взять айдишник юзера?
    //  Полагаю, что это можно сделать от Spring Security
    //  при выполнении запроса, который требует аутентификации
    //  от самого юзера
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(Ads.builder().build());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Ad> updateImage(@PathVariable Long id,
                                          @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok(Ad.mapToDTo(adService.updateImage(id, multipartFile), modelMapper));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Long id) {
        return ResponseEntity.ok(
                Comments.mapToDTO(
                        commentService.findCommentsByAdId(id), modelMapper));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id,
                                              @RequestBody Comment comment) {

        return ResponseEntity.ok(Comment.mapToDTO(
                Comment.mapToEntity(comment, adService.get(id)), modelMapper));
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
