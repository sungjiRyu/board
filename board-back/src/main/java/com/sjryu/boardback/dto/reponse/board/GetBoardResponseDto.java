package com.sjryu.boardback.dto.reponse.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sjryu.boardback.common.ResponseCode;
import com.sjryu.boardback.common.ResponseMessage;
import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.entity.ImageEntity;
import com.sjryu.boardback.repository.resultSet.GetBoardResultSet;

import lombok.Getter;


@Getter
public class GetBoardResponseDto extends ResponseDto {
    
    private int boardNumber;
    private String title;
    private String content;
    private List<String> boardImageList;
    private String writeDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

    private GetBoardResponseDto(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        super(ResponseCode.SUCCES, ResponseMessage.SUCCES);

        List<String> boardImageList = new ArrayList<>();
        for (ImageEntity imageEntity: imageEntities){
            String boardImage = imageEntity.getImgUrl();
            boardImageList.add(boardImage);
        }

        this.boardNumber = resultSet.getBoardSeq();
        this.title = resultSet.getBoardTitle();
        this.content = resultSet.getBoardContent();
        this.boardImageList = boardImageList;
        this.writeDatetime = resultSet.getBoardWriteDatetime();
        this.writerEmail = resultSet.getBoardUserEmail();
        this.writerNickname = resultSet.getUserNickname();
        this.writerProfileImage = getWriterProfileImage();
    }
    
    //  성공
    public static ResponseEntity<GetBoardResponseDto> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        GetBoardResponseDto result = new GetBoardResponseDto(resultSet, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    //  실패(존재하지 않는 게시물)
    public static ResponseEntity<ResponseDto> noExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
