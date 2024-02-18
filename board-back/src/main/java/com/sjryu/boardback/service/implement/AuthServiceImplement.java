package com.sjryu.boardback.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.dto.reponse.auth.SignUpResponseDto;
import com.sjryu.boardback.dto.request.auth.SignUpRequestDto;
import com.sjryu.boardback.entity.UserEntity;
import com.sjryu.boardback.repository.UserRepository;
import com.sjryu.boardback.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    // final로 지정해서 RequiredArgsConstructor 사용해서 생성자 만들어줌
    // 만들어진 생성자로 의존성 주입
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        
        try {
            
            // 이메일, 닉네임, 전화번호 중복검사
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByUserEmail(email);
            if (existedEmail)       return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByUserNickname(nickname);
            if (existedNickname)   return SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByUserTelNum(telNumber);
            if (existedTelNumber)   return SignUpResponseDto.duplicateTelnumber();
            
            // 비밀번호 암호화
            String password = dto.getPassword();
            String encodePassword = passwordEncoder.encode(password);
            dto.setPassword(encodePassword);

            // 유저 엔티티에 저장
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.succes();
    }
    
}
