package com.sparta.mini.post.repository;

import com.sparta.mini.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositoy extends JpaRepository<Post, Long> {
}
