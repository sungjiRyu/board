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

    Date now = Date.from(Instant.now());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String writeDatetime = simpleDateFormat.format(now);


    this.boardUserEmail = email;
    this.boardTitle = dto.getTitle();
    this.boardContent = dto.getContent();
    this.boardWriteDatetime = writeDatetime;
    this.boardFavoriteCnt = 0;
    this.boardViewCnt = 0;
    this.boardCommentCnt = 0;
        
    }

    public void increaseViewCount() {
        this.boardViewCnt++;
    }

}
