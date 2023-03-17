package com.sparta.mini.comment.service;

import com.sparta.mini.comment.dto.CommentRequestDto;
import com.sparta.mini.comment.entity.Comment;
import com.sparta.mini.comment.repository.CommentRepository;
import com.sparta.mini.member.dto.MessageResponseDto;
import com.sparta.mini.member.entity.Member;
import com.sparta.mini.post.entity.Post;
import com.sparta.mini.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

//    1. 댓글 작성 메서드
    @Transactional
    public MessageResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, Member member) {
//        게시글 존재 여부 확인. 없으면 예외처리
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Comment comment = new Comment(post, member, commentRequestDto);
        commentRepository.save(comment);
        return MessageResponseDto(HttpStatus.OK, "댓글 작성 성공");
    }

//    2. 댓글 수정 메서드
    @Transactional
    public MessageResponseDto updateComment(Long postId, Long commentId, CommentRequestDto commentRequestDto, Member member) {
//        게시글 존재 여부 확인. 없으면 예외처리
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
//        댓글 존재 여부 확인. 없으면 예외처리
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
//        ADMIN이 아닌 멤버가 댓글의 해당 작성자가 아닐때 예외 처리
        if (!comment.getMember().getId().equals(member.getId()) && member.getRole().equals(UserRoleEnum.ADMIN)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        comment.update(commentRequestDto);
        return MessageResponseDto(HttpStatus.OK, "댓글 수정 성공");
    }

//    3. 댓글 삭제 메서드
    @Transactional
    public MessageResponseDto deleteComment(Long postId, Long commentId , Member member) {
        //        게시글 존재 여부 확인. 없으면 예외처리
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
//        댓글 존재 여부 확인. 없으면 예외처리
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
//        ADMIN이 아닌 멤버가 댓글의 해당 작성자가 아닐때 예외 처리
        if (!comment.getMember().getId().equals(member.getId()) && member.getRole().equals(UserRoleEnum.ADMIN)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        commentRepository.deleteById(commentId);
        return MessageResponseDto(HttpStatus.OK, "댓글 삭제 성공");
    }




}
