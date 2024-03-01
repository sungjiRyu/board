package com.sjryu.boardback.service;

import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.dto.reponse.board.GetBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetFavoriteListResponseDto;
import com.sjryu.boardback.dto.reponse.board.PostBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.PutFavoriteResponseDto;
import com.sjryu.boardback.dto.reponse.board.PostCommentResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetCommentListResponseDto;
import com.sjryu.boardback.dto.reponse.board.IncreaseViewCountResponseDto;
import com.sjryu.boardback.dto.reponse.board.DeleteBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.PatchBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetLatestBoardListResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetTop3BoardListResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetSearchBoardListResponseDto;

import com.sjryu.boardback.dto.request.board.PatchBoardRequestDto;
import com.sjryu.boardback.dto.request.board.PostBoardRequestDto;
import com.sjryu.boardback.dto.request.board.PostCommentRequestDto;

public interface BoardService {

    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);
    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);

    
}
