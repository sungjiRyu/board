package com.sjryu.boardback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


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
    private LocalDate boardWriteDatetime;
    private int boardFavoriteCnt;
    private int boardViewCnt;
    private int boardCommentCnt;
    
}
