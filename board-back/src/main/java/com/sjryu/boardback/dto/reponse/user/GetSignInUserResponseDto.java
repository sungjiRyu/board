package com.sjryu.boardback.dto.reponse.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ResponseDto {

    private String email;
    private String nickname;
    private String profileImage;


    private GetSignInUserResponseDto(UserEntity userEntity) {
       super(ResponseCode.SUCCES, ResponseMessage.SUCCES);
       this.email = userEntity.getUserEmail();
       this.nickname = userEntity.getUserNickname();
       this.profileImage = userEntity.getUserProfileImg();
      
    }

    //  성공
    public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity) {
        GetSignInUserResponseDto result = new GetSignInUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //  실패(존재하지않는 유저)
    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    
    
}
