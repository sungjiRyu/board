package com.sjryu.boardback.dto.reponse.board;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.object.FavoriteListItem;
import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.repository.resultSet.GetFavoriteListResultSet;

import lombok.Getter;

@Getter
public class GetFavoriteListResponseDto extends ResponseDto{
    
    private List<FavoriteListItem> favoriteList;
    
    private GetFavoriteListResponseDto(List<GetFavoriteListResultSet> resultSets) {
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);
        this.favoriteList = FavoriteListItem.copyList(resultSets);
    }

    //  성공
    public static ResponseEntity<GetFavoriteListResponseDto> success(List<GetFavoriteListResultSet> resultSets){
        GetFavoriteListResponseDto result = new GetFavoriteListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //  실패(존재하지 않는 게시물)
    public static ResponseEntity<ResponseDto> noExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
