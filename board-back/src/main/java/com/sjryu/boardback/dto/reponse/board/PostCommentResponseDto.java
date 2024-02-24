package com.sjryu.boardback.dto.reponse.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.reponse.ResponseDto;

import lombok.Getter;

@Getter
public class PostCommentResponseDto extends ResponseDto {

    public PostCommentResponseDto() {
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);
    }

    //  성공
    public static ResponseEntity<PostCommentResponseDto> sucess() {
        PostCommentResponseDto result = new PostCommentResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    //  실패(존재하지 않는 게시물)
    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    //  실패(존재하지 않는 유저)
    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
