package com.sjryu.boardback.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sjryu.boardback.dto.request.auth.SignUpRequestDto;
import com.sjryu.boardback.dto.reponse.auth.SignUpResponseDto;
import com.sjryu.boardback.dto.request.auth.SignInRequestDto;
import com.sjryu.boardback.dto.request.auth.SignUpEmailCheckDto;
import com.sjryu.boardback.dto.reponse.auth.SignInResponseDto;

import com.sjryu.boardback.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @GetMapping("/sign-up/checkemail")
    public ResponseEntity<? super SignUpResponseDto> checkEmail(
        @RequestBody @Valid SignUpEmailCheckDto requestBody
    ) {
        ResponseEntity<? super SignUpResponseDto> response = authService.checkEmail(requestBody);
        return response;
    }
    
    
}
