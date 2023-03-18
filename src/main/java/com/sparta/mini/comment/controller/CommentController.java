package com.sparta.mini.comment.controller;

import com.sparta.mini.comment.dto.CommentRequestDto;
import com.sparta.mini.comment.service.CommentService;
import com.sparta.mini.member.dto.MessageResponseDto;
import com.sparta.mini.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;

    //    1. 댓글 작성 API
    @PostMapping("/{postId}/comments")
    public ResponseEntity<MessageResponseDto> createComment(@PathVariable Long postId,
                                                                   @RequestBody @Valid CommentRequestDto commentRequestDto,
                                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(postId, commentRequestDto, userDetails.getMember());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "댓글 작성을 완료했습니다."));
    }

    //    2. 댓글 수정 API
    @PatchMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<MessageResponseDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId,
                                            @RequestBody @Valid CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(postId, commentId, commentRequestDto, userDetails.getMember());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "댓글 수정을 완료했습니다."));
    }

    //    3. 댓글 삭제 API
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<MessageResponseDto> deleteComment(@PathVariable Long postId, @PathVariable Long commentId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MessageResponseDto messageResponseDto = commentService.deleteComment(postId, commentId, userDetails.getMember());
        return ResponseEntity.ok(messageResponseDto);
    }

}