package com.sparta.mini.member.service;

import com.sparta.mini.jwt.JwtUtil;
import com.sparta.mini.member.dto.LoginRequestDto;
import com.sparta.mini.member.dto.MessageResponseDto;
import com.sparta.mini.member.dto.SignupRequestDto;
import com.sparta.mini.member.dto.StatusEnum;
import com.sparta.mini.member.entity.Member;
import com.sparta.mini.member.entity.MemberRoleEnum;
import com.sparta.mini.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MessageResponseDto signup(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String email = signupRequestDto.getEmail();
        String nickname = signupRequestDto.getNickname();

        Optional<Member> foundUsername = memberRepository.findByUsername(username);

        if(foundUsername.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
        }

        Optional<Member> foundEmail = memberRepository.findByEmail(email);

        if(foundEmail.isPresent()){
            throw new IllegalArgumentException("중복된 이메일이 존재합니다");
        }

        Optional<Member> foundNickname = memberRepository.findByNickname(nickname);

        if(foundUsername.isPresent()){
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다");
        }

        MemberRoleEnum role = MemberRoleEnum.USER;
        /*if(signupRequestDto.isAdmin()){
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 틀립니다");
            }
            role = MemberRoleEnum.ADMIN;
        }*/

        Member member = new Member(username, password, role);
        memberRepository.save(member);
        return new MessageResponseDto(StatusEnum.OK);
    }

    public MessageResponseDto login(LoginRequestDto loginRequestDto){
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );
        if (!member.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwt.createToken(member.getUsername(), member.getRole()));
        return new MessageResponseDto(HttpStatus.OK, "로그인이 완료되었습니다.");
    }

}
