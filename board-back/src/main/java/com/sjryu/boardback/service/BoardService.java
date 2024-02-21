package com.sjryu.boardback.service;

import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.dto.reponse.board.PostBoardResponseDto;
import com.sjryu.boardback.dto.request.board.PostBoardRequestDto;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email)  ;


    
}
