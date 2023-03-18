package com.sparta.mini.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {

    @NotNull(message = "username을 입력해주세요")
//    @Pattern(regexp = "^*[a-z0-9]{5,12}$", message = "알파벳 소문자(a-z), 숫자(0~9)만 입력 가능")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{5,12}", message = "알파벳 소문자(a-z), 숫자(0~9)만 입력 가능합니다.")
    //@Size(min = 5, message = "최소 5자 이상")
    private String username;

    @NotNull(message = "password을 입력해주세요")
//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*[@#$!%*?&])[A-Za-z\\\\d@#$!%*?&]{5,15}$\", message = \"5~15글자, 글자 1개, 숫자 1개, 특수문자 1개 꼭 입력해야합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{5,15}$", message = "5~15글자, 글자 1개, 숫자 1개, 특수문자 1개 꼭 입력해야합니다.")
    //@Size(min = 8, message = "최소 5자 이상")
    private String password;

    private String nickname;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private boolean admin = false;
    private String admintoken = "";
}
