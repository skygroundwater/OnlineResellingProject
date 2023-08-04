package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {


    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(Ads.builder().build());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestBody Ad ad,
                                    @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(ExtendedAd.builder().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeAd(@PathVariable Integer id) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id) {
        return ResponseEntity.ok(Ad.builder().build());
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(Ads.builder().build());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<String> updateImage(@PathVariable Integer id,
                                              @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok("путь к файлу");
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(Comments.builder()
                .count(3)
                .build());
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id, @RequestBody Comment comment) {
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Integer id, @PathVariable Integer commentId) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody Comment comment) {
        return ResponseEntity.ok(comment);
    }

}
