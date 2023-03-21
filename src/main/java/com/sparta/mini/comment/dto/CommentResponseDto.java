package com.sparta.mini.comment.dto;

import com.sparta.mini.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CommentResponseDto {

    private Long id;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private boolean isViewerRoleAdmin = false;


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.nickname = comment.getMember().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

    public CommentResponseDto(Comment comment, boolean isAdmin) {
        this.id = comment.getId();
        this.nickname = comment.getMember().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.isViewerRoleAdmin = isAdmin;
    }
}