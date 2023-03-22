package com.sparta.mini.member.controller;

import com.sparta.mini.member.dto.LoginRequestDto;
import com.sparta.mini.member.dto.LoginResponseDto;
import com.sparta.mini.member.dto.MessageResponseDto;
import com.sparta.mini.member.dto.SignupRequestDto;
import com.sparta.mini.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        memberService.signup(signupRequestDto);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "test123123 회원가입이 성공적으로 진행되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
       LoginResponseDto loginResponseDto = memberService.login(loginRequestDto, response);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
//        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "test123123213 로그인이 성공적으로 진행되었습니다."));
    }
}
