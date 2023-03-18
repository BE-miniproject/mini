package com.sparta.mini.post.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
public class PostRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String classNumber;
    @NotBlank
    private String specialty;
}
