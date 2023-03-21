package com.sparta.mini.member.service;

import com.sparta.mini.jwt.JwtUtil;
import com.sparta.mini.member.dto.LoginRequestDto;
import com.sparta.mini.member.dto.LoginResponseDto;
import com.sparta.mini.member.dto.SignupRequestDto;
import com.sparta.mini.member.entity.Member;
import com.sparta.mini.member.entity.MemberRoleEnum;
import com.sparta.mini.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Transactional
    public void signup(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
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

        if(foundNickname.isPresent()){
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다");
        }

        MemberRoleEnum role = MemberRoleEnum.USER;
        if(signupRequestDto.isAdmin()){
            if (!signupRequestDto.getAdmintoken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 틀립니다");
            }
            role = MemberRoleEnum.ADMIN;
        }


        Member member = new Member(username, password, role, email, nickname);
        memberRepository.save(member);
    }
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );
        if (!passwordEncoder.matches(password, member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getUsername(), member.getRole()));
        return new LoginResponseDto(member.getNickname());
    }

}
