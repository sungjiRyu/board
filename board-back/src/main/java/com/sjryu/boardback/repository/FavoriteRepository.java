package com.sjryu.boardback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sjryu.boardback.entity.FavoriteEntity;
import com.sjryu.boardback.entity.primaryKey.FavoritePk;
import com.sjryu.boardback.repository.resultSet.GetFavoriteListResultSet;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk>{
    
    FavoriteEntity findByFavUserEmailAndFavBoardSeq(String userEmail, Integer boardNumber);

    @Query(
        value=
        "SELECT " +
            "U.user_email  AS userEmail, " +  
            "U.user_nickname AS userNickname, " +
            "U.user_profile_img AS userProfileImg " +
        "FROM favorite AS F " +
        "INNER JOIN user AS U " +
        "ON F.fav_user_email = U.user_email " +
        "WHERE F.fav_board_seq = ?1" ,
        nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber);
}
