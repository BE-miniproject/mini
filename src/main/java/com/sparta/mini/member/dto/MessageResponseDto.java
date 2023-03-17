package com.sparta.mini.member.dto;

import org.springframework.http.HttpStatus;

public class MessageResponseDto {
    private int status;
    private String msg;

    public MessageResponseDto(HttpStatus httpStatus, String msg){
        this.status = httpStatus.value();
        this.msg = msg;
    }
}
