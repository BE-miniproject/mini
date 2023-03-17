package com.sparta.mini.comment.controller;

import com.sparta.mini.comment.dto.CommentRequestDto;
import com.sparta.mini.comment.service.CommentService;
import com.sparta.mini.member.dto.MessageResponseDto;
import com.sparta.mini.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class CommentController {

    private final CommentService commentService;

//    1. 댓글 작성 API
    @PostMapping("/{postId}/comment")
    public MessageResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody @Valid CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails.getMember());
    }

//    2. 댓글 수정 API
    @PatchMapping("/{postId}/comment/{commentId}")
    public MessageResponseDto updateComment(@PathVariable Long postId, @PathVariable Long commentId,
                                            @RequestBody @Valid CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(postId, commentId, commentRequestDto, userDetails.getMember());
    }

//    3. 댓글 삭제 API
    @DeleteMapping("/{postId}/comment/{commentId}")
    public MessageResponseDto deleteComment(@PathVariable Long postId, @PathVariable Long commentId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(postId, commentId, userDetails.getMember());
    }

}
