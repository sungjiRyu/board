package com.sjryu.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sjryu.boardback.entity.BoardEntity;
import com.sjryu.boardback.repository.resultSet.GetBoardResultSet;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

    BoardEntity findByBoardSeq(Integer boardNumber);
    
    @Query(
        value = 
        "SELECT " +
        "B.board_seq AS boardSeq, "+
        "B.board_title AS boardTitle, "+
        "B.board_content AS boardContent, "+
        "B.board_write_datetime AS boardWriteDatetime, "+
        "B.board_user_email AS boardUserEmail, "+
        "U.user_nickname AS userNickname, "+
        "U.user_profile_img AS userProfileImg "+
        "FROM board as B "+
        "INNER JOIN user as U "+
        "ON B.board_user_email = U.user_email "+
        "WHERE board_seq = ?1",
        nativeQuery = true
    )
    GetBoardResultSet getBoard(Integer boardNumber);
    
}
