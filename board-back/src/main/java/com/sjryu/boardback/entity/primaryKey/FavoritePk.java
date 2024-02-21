package com.sjryu.boardback.entity.primaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import jakarta.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePk implements Serializable {
    @Column(name="fav_user_email")
    private String favUserEmail;

    @Column(name="fav_board_seq")
    private int favBoardSeq;
}
