package com.sparta.mini.comment.entity;

import com.sparta.mini.comment.dto.CommentRequestDto;
import com.sparta.mini.member.entity.Member;
import com.sparta.mini.post.entity.Post;
import com.sparta.mini.post.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @NotBlank
    private String content;

    public Comment(Post post, Member member, CommentRequestDto commentRequestDto) {
        this.post = post;
        this.member = member;
        this.content = commentRequestDto.getContent();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

}
