package com.sjryu.boardback.entity;

import com.sjryu.boardback.dto.request.board.PostCommentRequestDto;
import com.sjryu.boardback.util.dateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="comment")
@Table(name="comment")
public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentSeq;
    private int commentBoardSeq;
    private String commentUserEmail;
    private String commentContent;
    private String commentWriteDatetime;

    public CommentEntity (PostCommentRequestDto dto, Integer boardNumber, String email){
    
    String writeDateime = dateTime.simpleDateTime();

    this.commentBoardSeq = boardNumber;
    this.commentUserEmail = email;
    this.commentContent = dto.getContent();
    this.commentWriteDatetime = writeDateime;
    }
    
    
}
