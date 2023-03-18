package com.sparta.mini.post.dto;

import com.sparta.mini.comment.dto.CommentResponseDto;
import com.sparta.mini.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostEntireDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;
    private String classNumber;
    private String specialty;

    public PostEntireDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt();
        this.classNumber = post.getClassNumber();
        this.specialty = post.getSpecialty();
    }
}
