package com.sparta.mini.comment.dto;

import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class CommentRequestDto {

    @Size(min = 1, max = 100, message = "댓글은 최소 1자에서 최대 100자 이내여야 합니다.")
    private String content;
}