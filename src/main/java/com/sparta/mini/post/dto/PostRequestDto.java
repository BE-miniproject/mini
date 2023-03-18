package com.sparta.mini.post.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
public class PostRequestDto {


    @Size(min = 3,max = 25,message = "제목은 3자 이상 25자 이하여야 합니다.")
    private String title;

    @Size(min = 10, max = 2000,message = "내용은 10자 이상 2000자 이하여야 합니다.")
    private String content;
    @NotBlank(message = "기수를 선택해주세요.")
    private String classNumber;
    @NotBlank(message = "주특기를 선택해주세요.")
    private String specialty;
}
