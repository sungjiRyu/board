package com.sjryu.boardback.dto.reponse.board;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.object.BoardListItem;
import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.entity.BoardListViewEntity;

import lombok.Getter;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetLatestBoardListResponseDto extends ResponseDto {

    private List<BoardListItem> latestList;

    private GetLatestBoardListResponseDto(List<BoardListViewEntity> boardEntities){
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);
        this.latestList = BoardListItem.getList(boardEntities);
    }

    public static ResponseEntity<GetLatestBoardListResponseDto> success(List<BoardListViewEntity>  boardListEntities){
        GetLatestBoardListResponseDto result = new GetLatestBoardListResponseDto(boardListEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
