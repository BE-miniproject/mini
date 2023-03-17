package com.sparta.mini.post.service;

import com.sparta.mini.member.entity.Member;
import com.sparta.mini.post.dto.PostRequestDto;
import com.sparta.mini.post.dto.PostResponseDto;
import com.sparta.mini.post.entity.Post;
import com.sparta.mini.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto/*, Member member*/) {
        Post post = postRepository.saveAndFlush(new Post(requestDto/*,member*/));
        return new PostResponseDto(post);
    }
}
