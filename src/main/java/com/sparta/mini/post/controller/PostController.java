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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

//    게시글 작성 API
    @PostMapping("/post") public ResponseEntity<MessageResponseDto> createPost(@Valid @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.createPost(requestDto,userDetails.getMember());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 작성을 완료했습니다."));
    }

//    전체 게시글 조회 API
    @GetMapping("/post")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

//    상세 게시글 조회 API
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId){
        PostResponseDto postResponseDto = postService.getpost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

//    게시글 수정 API
    @PatchMapping("/post/{postId}")
    public ResponseEntity<MessageResponseDto> updatePost(@PathVariable Long postId,@Valid @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.update(postId, requestDto, userDetails.getMember());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 수정을 완료했습니다."));
    }

//    게시글 삭제 API
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<MessageResponseDto> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.deletePost(postId, userDetails.getMember());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "게시글 삭제를 완료했습니다."));
    }


}
