package com.sparta.mini.member.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,12}", message = "5~12글자, 알파벳 소문자나 숫자를 최소 하나씩 입력해야 합니다.")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{5,15}$", message = "5~15글자, 알파벳, 숫자, 특수문자를 최소 하나씩 입력해야 합니다.")
    private String password;

    @NotBlank(message = "nickname을 입력해주세요.")
    private String nickname;

    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private boolean admin = false;
    private String admintoken = "";
}
