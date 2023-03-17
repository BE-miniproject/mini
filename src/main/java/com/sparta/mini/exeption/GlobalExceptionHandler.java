package com.sparta.mini.exeption;

import com.sparta.mini.member.dto.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //    throw로 발생시킨 오류 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponseDto> handleRuntimeException(RuntimeException e){
        String message = e.getMessage().split(":")[0];
        MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.BAD_REQUEST);
    }

    //    @Valid 오류 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponseDto> handleNotValidException(MethodArgumentNotValidException e){
        String message = e.getFieldError().getDefaultMessage();
        MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.BAD_REQUEST);
    }

    //    나머지 예외 처리
    @ExceptionHandler
    public ResponseEntity<MessageResponseDto> handleException(Exception e){
        String message = e.getMessage().split(":")[0];
        MessageResponseDto messageResponseDto = new MessageResponseDto(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.BAD_REQUEST);
    }



}
