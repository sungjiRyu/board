package com.sjryu.boardback.dto.object;

import com.sjryu.boardback.entity.BoardListViewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {
    private int boardNumber;
    private String title;
    private String content;
    private String boardTitleImage;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writeDatetime;
    private String writerNickname;
    private String writerProfileImage;
    
    public BoardListItem(BoardListViewEntity boardListViewEntity){
        this.boardNumber = boardListViewEntity.getBoardSeq();
        this.title = boardListViewEntity.getBoardTitle();
        this.content = boardListViewEntity.getBoardContent();
        this.boardTitleImage = boardListViewEntity.getImgUrl();
        this.favoriteCount = boardListViewEntity.getBoardFavoriteCnt();
        this.commentCount = boardListViewEntity.getBoardCommentCnt();
        this.viewCount = boardListViewEntity.getBoardViewCnt();
        this.writeDatetime = boardListViewEntity.getBoardWriteDatetime();
        this.writerNickname = boardListViewEntity.getUserNickname();
        this.writerProfileImage = boardListViewEntity.getUserProfileImg();
    }

    public static List<BoardListItem> getList(List<BoardListViewEntity> boardListViewEntities) {
        List<BoardListItem> list = new ArrayList<>();
        for(BoardListViewEntity boardListViewEntity: boardListViewEntities){
            BoardListItem boardListItem = new BoardListItem(boardListViewEntity);
            list.add(boardListItem);
        }
        return list;
    }

}
