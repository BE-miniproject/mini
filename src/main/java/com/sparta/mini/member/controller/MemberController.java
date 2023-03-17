package com.sparta.mini.member.controller;

import com.sparta.mini.member.dto.LoginRequestDto;
import com.sparta.mini.member.dto.SignupRequestDto;
import com.sparta.mini.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public ModelAndView signupPage(){
        return new ModelAndView("signup");
    }
    @GetMapping("/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @PostMapping("/signup")//test 전에 @Valid 죽여 놓기
    public ResponseEntity signup(@RequestBody SignupRequestDto signupRequestDto){
        return ResponseEntity.ok().body(memberService.signup(signupRequestDto));
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
        memberService.login(loginRequestDto);
        return ResponseEntity.ok().body(memberService.login(loginRequestDto));
    }
}
