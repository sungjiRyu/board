package com.sjryu.boardback.dto.reponse.search;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.repository.resultSet.GetRelationListResultSet;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class GetRelationListResponseDto extends ResponseDto {

    private List<String> relativeWordList;

    private GetRelationListResponseDto(List<GetRelationListResultSet> resultSets) {
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);

        List<String> relativeWordList = new ArrayList<>();

        for(GetRelationListResultSet resultSet : resultSets){
            String relativeWord = resultSet.getSearchWord();
            relativeWordList.add(relativeWord);
        }

        this.relativeWordList = relativeWordList;
    }

        public static ResponseEntity<GetRelationListResponseDto> success(List<GetRelationListResultSet> resultSets){
            GetRelationListResponseDto result = new GetRelationListResponseDto(resultSets);
            return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
}
