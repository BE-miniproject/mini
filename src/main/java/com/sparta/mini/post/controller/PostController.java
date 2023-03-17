package com.sparta.mini.post.controller;

import com.sparta.mini.member.dto.MessageResponseDto;
import com.sparta.mini.post.dto.PostRequestDto;
import com.sparta.mini.post.dto.PostResponseDto;
import com.sparta.mini.post.service.PostService;
import com.sparta.mini.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    //    1. 게시글 작성 API
    @PostMapping("/post") public ResponseEntity<MessageResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.createPost(requestDto,userDetails.getMember);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 작성 성공"));
    }
    //    2. 게시글 정보 API
    @GetMapping("/post")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    //    3. 상세 게시글 정보 API

    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponseDto> getpost(@PathVariable Long id){
        PostResponseDto postResponseDto = postService.getpost(id);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    //    4. 게시글 수정 API

    @PatchMapping("/post/{id}")
    public ResponseEntity<MessageResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.update(id, requestDto, userDetails.getMember());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 수정 성공"));
    }

    //    5. 게시글 삭제 API

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<MessageResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.deletePost(id, userDetails.getMember());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 삭제 성공")));
    }


}
