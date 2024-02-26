package com.sjryu.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjryu.boardback.entity.ImageEntity;

import jakarta.transaction.Transactional;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    
    List<ImageEntity> findByImgBoardSeq(Integer imgBoardSeq);

    @Transactional
    void deleteByImgBoardSeq (Integer boardNumber);

}
