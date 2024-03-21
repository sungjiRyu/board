package com.sjryu.boardback.service.implement;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.dto.reponse.user.GetSignInUserResponseDto;
import com.sjryu.boardback.dto.reponse.user.GetUserResponseDto;
import com.sjryu.boardback.dto.reponse.user.PatchNicknameResponseDto;
import com.sjryu.boardback.dto.reponse.user.PatchProfileImageResponseDto;
import com.sjryu.boardback.dto.request.user.PatchNicknameRequestDto;
import com.sjryu.boardback.dto.request.user.PatchProfileImageRequestDto;
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

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

        UserEntity userEntity = null;

        try {
            userEntity = userRespository.findByUserEmail(email);
            if(userEntity == null) return GetUserResponseDto.noExistUser(); 

        } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email) {
        try {

            UserEntity userEntity = userRespository.findByUserEmail(email);
            if(userEntity == null) PatchNicknameResponseDto.noExistUser();

            String nickname = dto.getNickname();
            boolean existedNickname = userRespository.existsByUserNickname(nickname);
            if (existedNickname) return PatchNicknameResponseDto.duplicate();

            userEntity.setNickname(nickname);
            userRespository.save(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {
        try {

            UserEntity userEntity = userRespository.findByUserEmail(email);
            if(userEntity == null) PatchProfileImageResponseDto.noExistUser();

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRespository.save(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchProfileImageResponseDto.success();
        
    }
    
}
