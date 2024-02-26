package com.sjryu.boardback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sjryu.boardback.entity.CommentEntity;
import com.sjryu.boardback.repository.resultSet.GetCommentListResultSet;

import jakarta.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{ 
 
    @Query(
        value=
        "SELECT  "+
        "   U.user_nickname AS userNickname, " +
        "   U.user_profile_img AS userProfile_img, " +
        "   C.comment_write_datetime AS commentWriteDatetime, " +
        "   C.comment_content AS commentContent " + 
        "FROM comment AS C " +
        "INNER JOIN user AS U " +
        "ON C.comment_user_email = U.user_email  " +
        "WHERE C.comment_board_seq = ?1 " +
        "ORDER BY comment_write_datetime DESC", 
        nativeQuery=true
    )
    List<GetCommentListResultSet> getCommentList(Integer boardNumber);

    @Transactional
    void deleteByCommentBoardSeq (Integer boardNumber);
}
