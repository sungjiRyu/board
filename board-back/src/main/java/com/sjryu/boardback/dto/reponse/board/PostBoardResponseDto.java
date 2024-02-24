package com.sjryu.boardback.dto.reponse.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.reponse.ResponseDto;

import lombok.Getter;

@Getter
public class PostBoardResponseDto extends ResponseDto {

    private PostBoardResponseDto() {
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);
    }

    // 성공
    public static ResponseEntity<PostBoardResponseDto> success() {

        PostBoardResponseDto result = new PostBoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 실패(존재하지 않는 유저)
    public static ResponseEntity<ResponseDto> notExistUser() {

        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}
