package com.sjryu.boardback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board_list_view")
@Table(name="board_list_view")
public class BoardListViewEntity {

    @Id
    private int boardSeq;
    private String boardUserEmail;
    private String boardTitle;
    private String boardContent;
    private String imgUrl;
    private int boardViewCnt;
    private int boardFavoriteCnt;
    private int boardCommentCnt;
    private String boardWriteDatetime;
    private String userNickname;
    private String userProfileImg;
}
