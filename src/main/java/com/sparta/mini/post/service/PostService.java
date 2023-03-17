package com.sparta.mini.post.service;

import com.sparta.mini.member.entity.Member;
import com.sparta.mini.post.dto.PostRequestDto;
import com.sparta.mini.post.dto.PostResponseDto;
import com.sparta.mini.post.entity.Post;
import com.sparta.mini.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, Member member) {
        Post post = postRepository.saveAndFlush(new Post(requestDto, member));
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        return posts.stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getpost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시물은 존재하지 않습니다."));
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto, Member member) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다.")
        );
        if (member.getRoleEnum() == UserRoleEnum.ADMIN || member.getId().equals(post.getMember().getId())) {
            post.update(requestDto);
            return new PostResponseDto(post);
        } else {
            throw new IllegalArgumentException("수정권한 없음");
        }
    }

    public Long deletePost(Long id, Member member) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다.")
        );
        if (member.getRoleEnum() == MemberRoleEnum.ADMIN || member.getId().equals(post.getMember().getId())) {
            postRepository.deleteById(id);
            return id;
        } else {
            throw new IllegalArgumentException("삭제 권한 없음");
        }
    }
}
