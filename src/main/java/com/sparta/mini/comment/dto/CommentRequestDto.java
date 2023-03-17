package com.sparta.mini.comment.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentRequestDto {

    @NotBlank
    private String content;
}