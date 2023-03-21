package com.sparta.mini.member.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "username을 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,12}", message = "username은 5~12글자, 알파벳 소문자나 숫자를 최소 하나씩 입력해야 합니다.")
    private String username;

    @NotBlank(message = "password를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{5,15}$", message = "password는 5~15글자, 알파벳, 숫자, 특수문자를 최소 하나씩 입력해야 합니다.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,15}$", message = "닉네임은 2~15글자, 한글, 알파벳, 숫자를 입력하셔야합니다.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private boolean admin = false;
    private String admintoken = "";
}
