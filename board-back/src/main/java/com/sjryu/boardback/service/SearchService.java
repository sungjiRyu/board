package com.sjryu.boardback.service;
import com.sjryu.boardback.dto.reponse.search.GetPopularListResponseDto;
import com.sjryu.boardback.dto.reponse.search.GetRelationListResponseDto;

import org.springframework.http.ResponseEntity;

public interface SearchService {
    
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);

}
