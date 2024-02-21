package com.sjryu.boardback.service.implement;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.dto.reponse.user.GetSignInUserResponseDto;
import com.sjryu.boardback.entity.UserEntity;
import com.sjryu.boardback.repository.UserRepository;
import com.sjryu.boardback.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRespository;
    
    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {
        
        UserEntity userEntity = null;


        try {
            userEntity = userRespository.findByUserEmail(email);
            if (userEntity == null) return GetSignInUserResponseDto.notExistUser();
            
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetSignInUserResponseDto.success(userEntity);
    }
    
}
