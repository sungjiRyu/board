package com.sjryu.boardback.service;

import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.dto.reponse.user.GetSignInUserResponseDto;
import com.sjryu.boardback.dto.reponse.user.GetUserResponseDto;
import com.sjryu.boardback.dto.reponse.user.PatchProfileImageResponseDto;

import com.sjryu.boardback.dto.reponse.user.PatchNicknameResponseDto;
import com.sjryu.boardback.dto.request.user.PatchNicknameRequestDto;
import com.sjryu.boardback.dto.request.user.PatchProfileImageRequestDto;

public interface UserService {
    
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);

}
