package com.sparta.mini.member.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequestDto {
    @NotBlank(message = "username을 입력해주세요.")
    private String username;
    @NotBlank(message = "password를 입력해주세요.")
    private String password;
}
