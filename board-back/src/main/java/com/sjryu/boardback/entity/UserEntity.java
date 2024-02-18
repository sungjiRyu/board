package com.sjryu.boardback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {
    
    @Id
    private String userEmail;
    
    private String userPassword;
    
    private String userNickame;
    
    private String userTelNum;
    
    private String userAddress;
    
    private String userAddressDetail;
    
    private String userProfileImg;
    
}
