package com.sjryu.boardback.dto.reponse.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.reponse.ResponseDto;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ResponseDto {
    
    private String token;
    private int expirationTime;

    private SignInResponseDto(String token) {
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<SignInResponseDto> succes(String token) {
        SignInResponseDto result = new SignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> signInFailed(){
        ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
