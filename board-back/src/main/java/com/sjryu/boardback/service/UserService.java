package com.sjryu.boardback.service;

import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.dto.reponse.user.GetSignInUserResponseDto;

public interface UserService {
    
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
}
