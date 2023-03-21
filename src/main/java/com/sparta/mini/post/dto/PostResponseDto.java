package com.sparta.mini.post.dto;

import com.sparta.mini.comment.dto.CommentResponseDto;
import com.sparta.mini.comment.entity.Comment;
import com.sparta.mini.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private String classNumber;
    private String specialty;
    private List<CommentResponseDto> commentList = new ArrayList<>();
    private boolean isViewerRoleAdmin = false;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt();
        this.classNumber = post.getClassNumber();
        this.specialty = post.getSpecialty();
        List<Comment> comments = post.getCommentList();
        if (!comments.isEmpty()) {
            List<CommentResponseDto> commentList = new ArrayList<>();
            for (Comment comment : comments) {
                commentList.add(new CommentResponseDto(comment));
            }
            this.commentList = commentList;
        }
    }
    public PostResponseDto(Post post, boolean isAdmin) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt();
        this.classNumber = post.getClassNumber();
        this.specialty = post.getSpecialty();
        this.isViewerRoleAdmin = isAdmin;
        List<Comment> comments = post.getCommentList();
        if (!comments.isEmpty()) {
            List<CommentResponseDto> commentList = new ArrayList<>();
            for (Comment comment : comments) {
                commentList.add(new CommentResponseDto(comment));
            }
            this.commentList = commentList;
        }
    }
}
