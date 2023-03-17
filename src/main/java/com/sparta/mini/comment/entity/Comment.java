package com.sparta.mini.comment.entity;

<<<<<<< HEAD
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
=======
import com.sparta.mini.comment.dto.CommentRequestDto;
import com.sparta.mini.member.entity.Member;
import com.sparta.mini.post.entity.Post;
import com.sparta.mini.post.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
>>>>>>> e796d176bc60d3a4df80a556b7912fe17adfd8b5

@Entity
@NoArgsConstructor
@Getter
<<<<<<< HEAD
public class Comment {
=======
public class Comment extends Timestamped {
>>>>>>> e796d176bc60d3a4df80a556b7912fe17adfd8b5

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
=======
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

>>>>>>> e796d176bc60d3a4df80a556b7912fe17adfd8b5
}
