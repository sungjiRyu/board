package com.sjryu.boardback.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="image")
@Table(name="image")
public class ImageEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgSeq;
    private int imgBoardSeq;
    private String imgUrl;
    

    public ImageEntity (int boardSeq, String image){
        this.imgBoardSeq = boardSeq;
        this.imgUrl = image;
    }
}
