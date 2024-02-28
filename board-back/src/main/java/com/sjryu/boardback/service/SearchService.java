package com.sjryu.boardback.service;
import com.sjryu.boardback.dto.reponse.search.GetPopularListResponseDto;

import org.springframework.http.ResponseEntity;

public interface SearchService {
    
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();

}
