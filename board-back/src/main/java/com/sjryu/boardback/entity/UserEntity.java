package com.sjryu.boardback.entity;

import com.sjryu.boardback.dto.request.auth.SignUpRequestDto;

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
    private String userPwd;
    private String userNickname;
    private String userTelNum;
    private String userAddress;
    private String userAddressDetail;
    private String userProfileImg;
    private boolean agreedPersonal;

    public UserEntity (SignUpRequestDto dto){

        this.userEmail = dto.getEmail();
    
        this.userPwd = dto.getPassword();
        
        this.userNickname = dto.getNickname();
        
        this.userTelNum = dto.getTelNumber();
        
        this.userAddress = dto.getAddress();
        
        this.userAddressDetail = dto.getAddressDetail();

        this.agreedPersonal = dto.getAgreedPersonal();
    }
    
}
