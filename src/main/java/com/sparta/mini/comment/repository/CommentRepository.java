package com.sparta.mini.comment.repository;

import com.sparta.mini.comment.entity.Comment;
import com.sparta.mini.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByPost(Post post);
}