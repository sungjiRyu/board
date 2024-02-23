package com.sjryu.boardback.dto.reponse.board;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.object.CommentListItem;
import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.repository.resultSet.GetCommentListResultSet;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class GetCommentListResponseDto extends ResponseDto{
    
    private List<CommentListItem> commentList;


    private GetCommentListResponseDto(List<GetCommentListResultSet> resultSets){
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);
        this.commentList = CommentListItem.copyList(resultSets);
    }

    //  성공
    public static ResponseEntity<GetCommentListResponseDto> success(List<GetCommentListResultSet> resultSets){
        GetCommentListResponseDto result = new GetCommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //  실패(존재하지 않는 게시물)
    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
