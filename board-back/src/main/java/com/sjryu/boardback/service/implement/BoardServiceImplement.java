package com.sjryu.boardback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.PostBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.PutFavoriteResponseDto;
import com.sjryu.boardback.dto.request.board.PostBoardRequestDto;
import com.sjryu.boardback.entity.BoardEntity;
import com.sjryu.boardback.entity.FavoriteEntity;
import com.sjryu.boardback.entity.ImageEntity;
import com.sjryu.boardback.repository.BoardRepository;
import com.sjryu.boardback.repository.FavoriteRepository;
import com.sjryu.boardback.repository.ImageRepository;
import com.sjryu.boardback.repository.UserRepository;
import com.sjryu.boardback.repository.resultSet.GetBoardResultSet;
import com.sjryu.boardback.service.BoardService;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService{

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final BoardRepository boardRepository;
    private final FavoriteRepository favoriteRepository;

    //  게시물 조회
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {

        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try {

            resultSet = boardRepository.getBoard(boardNumber);
            if(resultSet == null) return GetBoardResponseDto.noExistBoard();

            imageEntities = imageRepository.findByImgBoardSeq(boardNumber);

            BoardEntity boardEntity = boardRepository.findByBoardSeq(boardNumber);
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
            

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return GetBoardResponseDto.success(resultSet, imageEntities);

    }

    //  게시물 작성
    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try {
            
            boolean existedEmail = userRepository.existsByUserEmail(email);
            if (!existedEmail) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            int boardNumber = boardEntity.getBoardSeq();

            for(String image : boardImageList){
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }
            // 반복문안에 save를 넣으면 DB접근량이 많아지기 때문에 saveAll 사용
            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardResponseDto.success();
    }

    //  좋아요
    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {
        
        try {

            boolean existedUser = userRepository.existsByUserEmail(email);
            if(!existedUser) return PutFavoriteResponseDto.noExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardSeq(boardNumber);
            if(boardEntity == null) return PutFavoriteResponseDto.noExistBoard();

            FavoriteEntity favoriteEntity = favoriteRepository.findByFavUserEmailAndFavBoardSeq(email, boardNumber);
            if(favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(email, boardNumber);
                favoriteRepository.save(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }
            //  이미 좋아요를 눌렀다면
            else {
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }

            boardRepository.save(boardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();

    }

    
    
}
