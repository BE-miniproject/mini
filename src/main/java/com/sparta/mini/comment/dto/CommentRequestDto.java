package com.sparta.mini.comment.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    @Size(min = 1, max = 100, message = "댓글은 최소 1자에서 최대 100자 이내여야 합니다.")
    private String content;
}