package com.sjryu.boardback.dto.reponse.search;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.repository.resultSet.GetPopularListResultSet;

import java.util.List;
import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class GetPopularListResponseDto extends ResponseDto {

    private List<String> popularWordList;

    private GetPopularListResponseDto(List<GetPopularListResultSet> resultSets) {
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);

        List<String> popularWordList = new ArrayList<>();

        for(GetPopularListResultSet resultSet : resultSets){
            String popularWord = resultSet.getSearchWord();
            popularWordList.add(popularWord);
        }

        this.popularWordList = popularWordList;
    }

    public static ResponseEntity<GetPopularListResponseDto> success(List<GetPopularListResultSet> resultSets) {
        GetPopularListResponseDto result = new GetPopularListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
     
}
