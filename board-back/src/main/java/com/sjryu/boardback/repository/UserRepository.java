package com.sjryu.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjryu.boardback.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

// 쿼리 메서드
    // existsBy(객체 ==   객체가존재하면 True 
    boolean existsByUserEmail(String email);
    boolean existsByUserNickname(String nickname);
    boolean existsByUserTelNum(String nickname);

    UserEntity findByUserEmail(String email);

}
