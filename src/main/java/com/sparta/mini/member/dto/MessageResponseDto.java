package com.sparta.mini.member.dto;

import org.springframework.http.HttpStatus;

public class MessageResponseDto {
    private int statusCode;
    private String msg;

    public MessageResponseDto(HttpStatus httpStatus, String msg){
        this.statusCode = httpStatus.value();
        this.msg = msg;
    }
}
