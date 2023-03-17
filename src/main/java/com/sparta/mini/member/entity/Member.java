package com.sparta.mini.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import javax.persistence.*;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
>>>>>>> e796d176bc60d3a4df80a556b7912fe17adfd8b5

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    public Member(String username, String password, MemberRoleEnum role){
        this.username =username;
        this.password = password;
        this.role = role;
    }

=======
>>>>>>> e796d176bc60d3a4df80a556b7912fe17adfd8b5
}
