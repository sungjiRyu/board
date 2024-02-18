package com.sjryu.boardback.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sjryu.boardback.dto.reponse.auth.SignUpResponseDto;
import com.sjryu.boardback.dto.request.auth.SignUpRequestDto;
import com.sjryu.boardback.service.AuthService;
import com.sjryu.boardback.service.implement.AuthServiceImplement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthServiceImplement authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signup(
        @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }
    
    
}
