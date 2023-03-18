package com.sparta.mini.post.entity;

import com.sparta.mini.comment.entity.Comment;
import com.sparta.mini.member.entity.Member;
import com.sparta.mini.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String classNumber;

    @Column(nullable = false)
    private String specialty;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    public Post(PostRequestDto requestDto,Member member) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.classNumber = requestDto.getClassNumber();
        this.specialty = requestDto.getSpecialty();
        this.member = member;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.classNumber = requestDto.getClassNumber();
        this.specialty = requestDto.getSpecialty();
    }
}
