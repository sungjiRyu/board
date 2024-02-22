package com.sjryu.boardback.repository.resultSet;

public interface GetBoardResultSet {
    Integer getBoardSeq();
    String getBoardTitle();
    String getBoardContent();
    String getBoardWriteDatetime();
    String getBoardUserEmail();
    String getUserNickname();
    String getUserProfileImg();
} 
