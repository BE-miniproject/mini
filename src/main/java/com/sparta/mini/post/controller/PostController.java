package com.sparta.mini.post.controller;

import com.sparta.mini.post.dto.PostRequestDto;
import com.sparta.mini.post.dto.PostResponseDto;
import com.sparta.mini.post.service.PostService;
import com.sparta.mini.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    @PostMapping("/post") public PostResponseDto createPost(@RequestBody PostRequestDto requestDto/*, @AuthenticationPrincipal UserDetailsImpl userDetails*/){
        return postService.createPost(requestDto/*,userDetails.getMember*/);
    }

}
