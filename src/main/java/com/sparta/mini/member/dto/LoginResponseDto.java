package com.sparta.mini.member.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String nickname;

    public LoginResponseDto(String nickname) {
        this.nickname = nickname;
    }
}
