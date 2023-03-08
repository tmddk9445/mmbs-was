package com.mong.mmbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.FindIdDto;
import com.mong.mmbs.dto.FindPasswordDto;
import com.mong.mmbs.dto.ResetPasswordDto;
import com.mong.mmbs.dto.SignInDto;
import com.mong.mmbs.dto.SignInResponseDto;
import com.mong.mmbs.dto.request.auth.SignUpRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.auth.SignUpPostResponseDto;
import com.mong.mmbs.entity.RecommendEntity;
import com.mong.mmbs.entity.UserEntity;
import com.mong.mmbs.repository.RecommendRepository;
import com.mong.mmbs.repository.UserRepository;
import com.mong.mmbs.security.TokenProvider;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RecommendRepository recommendRepository;
    @Autowired
    TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public ResponseDto<SignUpPostResponseDto> signUp(SignUpRequestDto dto) {

        String userId = dto.getUserId();
        if (userRepository.existsById(userId)) return ResponseDto.setFailed(ResponseMessage.EXIST_DATA);

        String userEmail = dto.getUserEmail();
        if (userRepository.existsByUserEmail(userEmail)) 
            return ResponseDto.setFailed(ResponseMessage.EXIST_DATA);

        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();

        if (!userPassword.equals(userPasswordCheck)) 
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);

        //# description: 실제 프로세스 //

        //# description: 추천인 //
        String recommendedUserId = dto.getRecommendedUserId();

        if (recommendedUserId != null && !recommendedUserId.isEmpty()) {

            //# description: 추천인 존재 여부 검증 //
            if (!userRepository.existsById(recommendedUserId)) 
                return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

            //# description: Entity 생성 //
            RecommendEntity recommendEntity = new RecommendEntity();
            
            try{

                recommendRepository.save(recommendEntity);

            } catch (Exception error) {
                return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
            }
            
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
        }
        
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userPassword);
        dto.setUserPassword(encodedPassword);

        UserEntity userEntity = new UserEntity(dto);

        try {

          userRepository.save(userEntity);        
          
        } catch (Exception error) {
          return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }

    //입력한 유저이름과 이메일이 둘다 맞는지만 판단하는 메서드
    public ResponseDto<?> findId(FindIdDto dto) {
    	String userName = dto.getUserName();
    	String userEmail = dto.getUserEmail();

    	UserEntity userEntity = userRepository.findByUserEmailAndUserName(userEmail, userName);
    	if (userEntity == null) return ResponseDto.setFailed("일치하는 정보가 없음"); 
    	return ResponseDto.setSuccess("성공", userEntity.getUserId());
    }
    
    public ResponseDto<?> findPassword(FindPasswordDto dto){
    	String userId = dto.getUserId();
    	String userName = dto.getUserName();
    	String userEmail = dto.getUserEmail();
    	
    	UserEntity userEntity = userRepository.findByUserIdAndUserNameAndUserEmail(userId, userName, userEmail);
    	if(userEntity == null) return ResponseDto.setFailed(" 일치하는 정보가 없음");
    	return ResponseDto.setSuccess("성공",null);
    }
    
    public ResponseDto<?> resetPassword(ResetPasswordDto dto){
    	String userId = dto.getUserId();
    	String password = dto.getUserPassword();
    	String password2 = dto.getUserPassword2();
    	
    	if(!password.equals(password2)) {
    		return ResponseDto.setFailed("Password Does Not matched!");
    	}
    		
    	UserEntity userEntity = userRepository.findByUserId(userId);
    	
    	String encodedPassword = passwordEncoder.encode(password);
    	userEntity.setUserPassword(encodedPassword);
    	userRepository.save(userEntity);
    	return ResponseDto.setSuccess("성공", null);
    }

    
    
    public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
    	// 필수로 아이디와 패스워드를 받아와야 한다. @NotBlank //
    	// 데이터가 있는지 검증 //
    	String userId = dto.getUserId();
    	String userPassword = dto.getUserPassword();
    	
    	UserEntity userEntity = null;
    	try {
    		userEntity = userRepository.findByUserId(userId);    
    		boolean matched = passwordEncoder.matches(userPassword, userEntity.getUserPassword());
    		if (!matched) return ResponseDto.setFailed("Password Not Matched");
    	} catch (Exception error) {
    		return ResponseDto.setFailed("DataBase Error");
    	}
    	
    	userEntity.setUserPassword("");
    	
    	String token = tokenProvider.create(userId);
    	int exprTime = 3600000;
    	
    	SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);
    	return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
    
    }
}

