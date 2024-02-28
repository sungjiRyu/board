package com.sjryu.boardback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjryu.boardback.entity.BoardListViewEntity;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer>{
    
    List<BoardListViewEntity> findByOrderByBoardWriteDatetimeDesc();

    List<BoardListViewEntity> findTop3ByBoardWriteDatetimeGreaterThanOrderByBoardFavoriteCntDescBoardCommentCntDescBoardViewCntDescBoardWriteDatetimeDesc(String writeDatetime);
}
