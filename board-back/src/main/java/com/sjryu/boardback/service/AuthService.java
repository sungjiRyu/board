package com.sjryu.boardback.service;

import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.dto.reponse.auth.SignInResponseDto;
import com.sjryu.boardback.dto.reponse.auth.SignUpResponseDto;
import com.sjryu.boardback.dto.request.auth.SignInRequestDto;
import com.sjryu.boardback.dto.request.auth.SignUpEmailCheckDto;
import com.sjryu.boardback.dto.request.auth.SignUpRequestDto;

public interface AuthService{

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> checkEmail(SignUpEmailCheckDto dto);
 }