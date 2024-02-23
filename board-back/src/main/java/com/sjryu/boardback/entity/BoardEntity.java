package com.sjryu.boardback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;

import com.sjryu.boardback.dto.request.board.PostBoardRequestDto;
import com.sjryu.boardback.util.dateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int boardSeq;
    private String boardUserEmail;
    private String boardTitle;
    private String boardContent;
    private String boardWriteDatetime;
    private int boardFavoriteCnt;
    private int boardViewCnt;
    private int boardCommentCnt;
    
    public BoardEntity(PostBoardRequestDto dto, String email) {

    String writeDatetime = dateTime.simpleDateTime();

    this.boardUserEmail = email;
    this.boardTitle = dto.getTitle();
    this.boardContent = dto.getContent();
    this.boardWriteDatetime = writeDatetime;
    this.boardFavoriteCnt = 0;
    this.boardViewCnt = 0;
    this.boardCommentCnt = 0;
        
    }
    //  조회수 +1
    public void increaseViewCount() {
        this.boardViewCnt++;
    }
    //  좋아요 +1
    public void increaseFavoriteCount() {
        this.boardFavoriteCnt++;
    }

    //  좋아요 -1
    public void decreaseFavoriteCount() {
        this.boardFavoriteCnt--;
    }

    //  댓글 갯수 +1
    public void increaseCommentCount() {
        this.boardCommentCnt++;    }


}
