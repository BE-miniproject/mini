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
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
//        this.nickname = post.getMember().getNickname;
        this.createdAt = post.getCreatedAt();
//        List<Comment> comments = post.getCommentList();
//        if (!comments.isEmpty()) {
//            List<CommentResponseDto> commentList = new ArrayList<>();
//            for (Comment comment : comments) {
//                commentList.add(new CommentResponseDto(comment));
//            }
//            this.commentList = commentList;
//        }
    }
}
