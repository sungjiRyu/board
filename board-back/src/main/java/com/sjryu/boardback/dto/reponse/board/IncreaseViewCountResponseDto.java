package com.sjryu.boardback.dto.reponse.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.reponse.ResponseDto;

public class IncreaseViewCountResponseDto extends ResponseDto {

     private IncreaseViewCountResponseDto() {
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);
    }

    // 성공
    public static ResponseEntity<IncreaseViewCountResponseDto> success() {
        IncreaseViewCountResponseDto result = new IncreaseViewCountResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    // 실패(존재하지않는 게시물)
    public static ResponseEntity<ResponseDto> noExistedBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
