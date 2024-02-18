package com.sjryu.boardback.service;

import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.dto.reponse.auth.SignUpResponseDto;
import com.sjryu.boardback.dto.request.auth.SignUpRequestDto;

public interface AuthService{

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

}