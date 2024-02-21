package com.sjryu.boardback.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sjryu.boardback.dto.reponse.ResponseDto;
import com.sjryu.boardback.dto.reponse.auth.SignInResponseDto;
import com.sjryu.boardback.dto.reponse.auth.SignUpResponseDto;
import com.sjryu.boardback.dto.request.auth.SignInRequestDto;
// import com.sjryu.boardback.dto.request.auth.SignUpEmailCheckDto;
import com.sjryu.boardback.dto.request.auth.SignUpRequestDto;
import com.sjryu.boardback.entity.UserEntity;
import com.sjryu.boardback.provider.JwtProvider;
import com.sjryu.boardback.repository.UserRepository;
import com.sjryu.boardback.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

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

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
     
        String token = null;

        try {

            String email = dto.getEmail();
            // email에 해당하는 user정보를 userEntity에 저장
            UserEntity userEntity = userRepository.findByUserEmail(email);
            // 이메일이 없다면
            if (userEntity == null) return SignInResponseDto.signInFailed();
            
            // 이메일이 존재한다면
            String password = dto.getPassword();
            String encodePassword = userEntity.getUserPwd();
            // 비밀번호 검증
            boolean isMatch = passwordEncoder.matches(password, encodePassword);
            // 비밀번호가 일치하지 않다면
            if (!isMatch) return SignInResponseDto.signInFailed();
            // 토큰 발행
            token = jwtProvider.create(email);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.succes(token);

    }


   
    
}
