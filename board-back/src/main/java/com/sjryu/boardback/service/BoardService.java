package com.sjryu.boardback.service;

import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.dto.reponse.board.GetBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetFavoriteListResponseDto;
import com.sjryu.boardback.dto.reponse.board.PostBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.PutFavoriteResponseDto;
import com.sjryu.boardback.dto.request.board.PostBoardRequestDto;

public interface BoardService {

    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email)  ;
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);

    
}
