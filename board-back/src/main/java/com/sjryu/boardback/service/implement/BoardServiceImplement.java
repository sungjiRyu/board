package com.sjryu.boardback.service.implement;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.dto.reponse.board.DeleteBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetCommentListResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetFavoriteListResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetLatestBoardListResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetSearchBoardListResponseDto;
import com.sjryu.boardback.dto.reponse.board.GetTop3BoardListResponseDto;
import com.sjryu.boardback.dto.reponse.board.IncreaseViewCountResponseDto;
import com.sjryu.boardback.dto.reponse.board.PatchBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.PostBoardResponseDto;
import com.sjryu.boardback.dto.reponse.board.PostCommentResponseDto;
import com.sjryu.boardback.dto.reponse.board.PutFavoriteResponseDto;
import com.sjryu.boardback.dto.request.board.PatchBoardRequestDto;
import com.sjryu.boardback.dto.request.board.PostBoardRequestDto;
import com.sjryu.boardback.dto.request.board.PostCommentRequestDto;
import com.sjryu.boardback.entity.BoardEntity;
import com.sjryu.boardback.entity.BoardListViewEntity;
import com.sjryu.boardback.entity.CommentEntity;
import com.sjryu.boardback.entity.FavoriteEntity;
import com.sjryu.boardback.entity.ImageEntity;
import com.sjryu.boardback.entity.SearchLogEntity;
import com.sjryu.boardback.repository.BoardListViewRepository;
import com.sjryu.boardback.repository.BoardRepository;
import com.sjryu.boardback.repository.CommentRepository;
import com.sjryu.boardback.repository.FavoriteRepository;
import com.sjryu.boardback.repository.ImageRepository;
import com.sjryu.boardback.repository.SearchLogRepository;
import com.sjryu.boardback.repository.UserRepository;
import com.sjryu.boardback.repository.resultSet.GetBoardResultSet;
import com.sjryu.boardback.repository.resultSet.GetCommentListResultSet;
import com.sjryu.boardback.repository.resultSet.GetFavoriteListResultSet;
import com.sjryu.boardback.service.BoardService;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService{

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final BoardRepository boardRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRespository;
    private final BoardListViewRepository boardListViewRepository;
    private final SearchLogRepository searchLogRepository;


    //  게시물 조회
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {

        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try {
            resultSet = boardRepository.getBoard(boardNumber);
            if(resultSet == null) return GetBoardResponseDto.noExistBoard();

            imageEntities = imageRepository.findByImgBoardSeq(boardNumber);

            
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return GetBoardResponseDto.success(resultSet, imageEntities);

    }

    //  좋아요 누른 사람 리스트 
    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedBoard = boardRepository.existsByBoardSeq(boardNumber);
            if(!existedBoard) return GetFavoriteListResponseDto.noExistBoard();
        
            resultSets = favoriteRepository.getFavoriteList(boardNumber);

        } catch (Exception exception) {
         exception.printStackTrace();
        }
        return GetFavoriteListResponseDto.success(resultSets);
    }

    //  댓글 목록 조회
    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {

        List<GetCommentListResultSet> resultSets = new ArrayList<>();

        try {
            
            boolean existedBoard = boardRepository.existsByBoardSeq(boardNumber);
            if(!existedBoard) return GetCommentListResponseDto.noExistBoard();

            resultSets = commentRespository.getCommentList(boardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return GetCommentListResponseDto.success(resultSets);
        
    }

    //  최신 게시물 조회
    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {

            boardListViewEntities =  boardListViewRepository.findByOrderByBoardWriteDatetimeDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetLatestBoardListResponseDto.success(boardListViewEntities);
        
    }

    //  주간 Top3 게시물 
    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {
            
            Date beforeWeek = Date.from(Instant.now().minus(7,ChronoUnit.DAYS));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sevenDaysAgo = simpleDateFormat.format(beforeWeek);

            boardListViewEntities = boardListViewRepository.findTop3ByBoardWriteDatetimeGreaterThanOrderByBoardFavoriteCntDescBoardCommentCntDescBoardViewCntDescBoardWriteDatetimeDesc(sevenDaysAgo);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTop3BoardListResponseDto.success(boardListViewEntities);
        
    }

    //  검색 게시물 리스트
    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord,
            String preSearchWord) {

                List<BoardListViewEntity>  boardListViewEntities = new ArrayList<>();

        try {

            boardListViewEntities = boardListViewRepository.findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc(searchWord, preSearchWord);
            
            SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord, preSearchWord, false);
            searchLogRepository.save(searchLogEntity);

            boolean relation = preSearchWord != null;
            if (relation) {
                searchLogEntity = new SearchLogEntity(preSearchWord, searchWord, relation);
                searchLogRepository.save(searchLogEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSearchBoardListResponseDto.success(boardListViewEntities);
        
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

    //  댓글 작성
    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email) {


        try {

            BoardEntity boardEntity = boardRepository.findByBoardSeq(boardNumber);
            if(boardEntity == null) return PostCommentResponseDto.noExistBoard();

            boolean existedUser = userRepository.existsByUserEmail(email);
            if(!existedUser) return PostCommentResponseDto.noExistBoard();

            CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
            commentRespository.save(commentEntity);
            
            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostCommentResponseDto.sucess();
    }

    //  좋아요 기능
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
                boardEntity.increaseFavoriteCount();
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

    //  게시물 수정
    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber,
            String email) {
       try {
        BoardEntity boardEntity = boardRepository.findByBoardSeq(boardNumber);
        if (boardEntity == null) return PatchBoardResponseDto.noExistBoard();
        
        boolean existedUser = userRepository.existsByUserEmail(email);
        if(!existedUser) return PatchBoardResponseDto.noExistUser();

        String writerEmail = boardEntity.getBoardUserEmail();
        if(writerEmail == null) return PatchBoardResponseDto.noPermission();

        boardEntity.patchBoard(dto);
        boardRepository.save(boardEntity);

        imageRepository.deleteByImgBoardSeq(boardNumber);
        List<String> boardImageList = dto.getBoardImageList();
        List<ImageEntity> imageEntities = new ArrayList<>();

        for (String image : boardImageList){
            ImageEntity imageEntity = new ImageEntity(boardNumber, image);
            imageEntities.add(imageEntity);
        }

        imageRepository.saveAll(imageEntities);

       } catch (Exception exception) {
            exception.printStackTrace();
       }
       return PatchBoardResponseDto.success();
    }

    //  조회수 증가
    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber) {
      try {

        BoardEntity boardEntity = boardRepository.findByBoardSeq(boardNumber);
        if (boardEntity == null) return IncreaseViewCountResponseDto.noExistedBoard();

        boardEntity = boardRepository.findByBoardSeq(boardNumber);
        boardEntity.increaseViewCount();
        boardRepository.save(boardEntity);

      } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
      }

      return IncreaseViewCountResponseDto.success();
    }

    //  게시물 삭제
    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {
        
       try {
        
        boolean existedUser = userRepository.existsByUserEmail(email);
        if(!existedUser) return DeleteBoardResponseDto.noExistUser();

        BoardEntity boardEntity = boardRepository.findByBoardSeq(boardNumber);
        if(boardEntity == null) return DeleteBoardResponseDto.noExistBoard();

        String writerEmail = boardEntity.getBoardUserEmail();
        boolean isWriter = writerEmail.equals(email);
        if(!isWriter) return DeleteBoardResponseDto.noPermission();

        imageRepository.deleteByImgBoardSeq(boardNumber);
        favoriteRepository.deleteByFavBoardSeq(boardNumber);
        commentRespository.deleteByCommentBoardSeq(boardNumber);

        boardRepository.delete(boardEntity);
        
       } catch (Exception exception) {

        exception.printStackTrace();

       }
       return DeleteBoardResponseDto.success();
    }

    

    

    

    
    

    

   

    

    

    
    
}
