package com.sparta.mini.post.service;

import com.sparta.mini.comment.repository.CommentRepository;
import com.sparta.mini.member.entity.Member;
import com.sparta.mini.member.entity.MemberRoleEnum;
import com.sparta.mini.post.dto.PostEntireDto;
import com.sparta.mini.post.dto.PostRequestDto;
import com.sparta.mini.post.dto.PostResponseDto;
import com.sparta.mini.post.entity.Post;
import com.sparta.mini.post.repository.PostRepository;
import com.sparta.mini.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, Member member) {
        Post post = postRepository.saveAndFlush(new Post(requestDto, member));
        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostEntireDto> getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        return posts.stream().map(PostEntireDto::new).toList();
    }

    @Transactional(readOnly = true)
    public PostResponseDto getpost(Long id, Member member) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        boolean isAdmin = false;
        if (member.getRole() == MemberRoleEnum.ADMIN || member.getId().equals(post.getMember().getId())){
            isAdmin = true;
        }
        return new PostResponseDto(post, isAdmin);
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto, Member member) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다.")
        );
        if (member.getRole() == MemberRoleEnum.ADMIN || member.getId().equals(post.getMember().getId())) {
            post.update(requestDto);
            return new PostResponseDto(post);
        } else {
            throw new IllegalArgumentException("게시글 수정 권한이 없습니다.");
        }
    }

    @Transactional
    public Long deletePost(Long id, Member member) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다.")
        );

        if (member.getRole() == MemberRoleEnum.ADMIN || member.getId().equals(post.getMember().getId())) {
            commentRepository.deleteAllByPost(post);
            postRepository.deleteById(id);
            return id;
        } else {
            throw new IllegalArgumentException("게시글 삭제 권한이 없습니다.");
        }
    }
}
