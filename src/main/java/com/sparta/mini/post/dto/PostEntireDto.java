package com.sparta.mini.post.dto;

import com.sparta.mini.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostEntireDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;
    private String classNumber;
    private String specialty;
    private int commentCount;

    public PostEntireDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt().withNano(0);
        this.classNumber = post.getClassNumber();
        this.specialty = post.getSpecialty();
        this.commentCount = post.getCommentList().size();
    }
}
