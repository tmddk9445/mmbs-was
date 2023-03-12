package com.mong.mmbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.common.util.UserUtil;
import com.mong.mmbs.dto.request.auth.FindIdRequestDto;
import com.mong.mmbs.dto.request.auth.SendPasswordEmailRequestDto;
import com.mong.mmbs.dto.request.auth.SignInRequestDto;
import com.mong.mmbs.dto.request.auth.SignUpRequestDto;
import com.mong.mmbs.dto.request.auth.resetPasswordPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.auth.SignInPostResponseDto;
import com.mong.mmbs.dto.response.auth.SignUpPostResponseDto;
import com.mong.mmbs.dto.response.auth.FindIdPostResponseDto;
import com.mong.mmbs.dto.response.auth.ResetPasswordPostResponseDto;
import com.mong.mmbs.entity.RecommendEntity;
import com.mong.mmbs.entity.UserEntity;
import com.mong.mmbs.repository.RecommendRepository;
import com.mong.mmbs.repository.UserRepository;
import com.mong.mmbs.security.TokenProvider;

@Service
public class AuthService {

  @Autowired TokenProvider tokenProvider;

  @Autowired MailService mailService;

  @Autowired UserRepository userRepository;
  @Autowired RecommendRepository recommendRepository;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public ResponseDto<SignUpPostResponseDto> signUp(SignUpRequestDto dto) {

    SignUpPostResponseDto data = null;

    String userId = dto.getUserId();
    if (userRepository.existsById(userId))
      return ResponseDto.setFailed(ResponseMessage.EXIST_DATA);

    String userEmail = dto.getUserEmail();
    if (userRepository.existsByUserEmail(userEmail))
      return ResponseDto.setFailed(ResponseMessage.EXIST_DATA);

    String userPassword = dto.getUserPassword();
    String userPasswordCheck = dto.getUserPasswordCheck();
    if (!userPassword.equals(userPasswordCheck))
      return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);

    String recommendedUserId = dto.getRecommendedUserId();

    RecommendEntity recommendEntity = new RecommendEntity();

    if (recommendedUserId != null && !recommendedUserId.isEmpty()) {

      if (!userRepository.existsById(recommendedUserId))
        return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

      try {

        recommendRepository.save(recommendEntity);

      } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
      }

    }

    String encodedPassword = passwordEncoder.encode(userPassword);
    dto.setUserPassword(encodedPassword);

    UserEntity userEntity = new UserEntity(dto);

    try {

      userRepository.save(userEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }

    data = new SignUpPostResponseDto(userEntity, recommendEntity);
    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

  }

  public ResponseDto<FindIdPostResponseDto> findId(FindIdRequestDto dto) {

    FindIdPostResponseDto data = null;

    String userEmail = dto.getUserEmail();
    String userName = dto.getUserName();

    try {

      UserEntity userEntity = userRepository.findByUserEmailAndUserName(userEmail, userName);

      if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);

      data = new FindIdPostResponseDto(userEntity.getUserId());

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }
    
    return ResponseDto.setSuccess("성공", data);

  }

  public ResponseDto<ResetPasswordPostResponseDto> resetPassword(resetPasswordPostRequestDto dto) {

    ResetPasswordPostResponseDto data = null;

    String userId = dto.getUserId();
    String password = dto.getUserPassword();
    String passwordCheck = dto.getUserPasswordCheck();

    try {

      if (!password.equals(passwordCheck)) return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);

      UserEntity userEntity = userRepository.findByUserId(userId);

      String encodedPassword = passwordEncoder.encode(password);
      userEntity.setUserPassword(encodedPassword);
      userRepository.save(userEntity);

      data = new ResetPasswordPostResponseDto(userEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }

    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
  }

  public ResponseDto<SignInPostResponseDto> signIn(SignInRequestDto dto) {

    SignInPostResponseDto data = null;
    UserEntity userEntity = null;

    String userId = dto.getUserId();
    String userPassword = dto.getUserPassword();

    try {

      userEntity = userRepository.findByUserId(userId);

      boolean matched = passwordEncoder.matches(userPassword, userEntity.getUserPassword());
      if (!matched)
        return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);

      userEntity.setUserPassword(ResponseMessage.NULL);

      String token = tokenProvider.create(userId);
      int exprTime = 3600000;

      data = new SignInPostResponseDto(token, exprTime, userEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }

    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

  }

  public ResponseDto<Boolean> sendPasswordEmail(SendPasswordEmailRequestDto dto) {

    String userEmail = dto.getUserEmail();

    try {
      UserEntity userEntity = userRepository.findByUserEmail(userEmail);
      if (userEmail == null) return ResponseDto.setFailed(null);

      String temporaryPassword = UserUtil.getTemporaryPassword();
      String encodedPassword = passwordEncoder.encode(temporaryPassword);

      userEntity.setUserPassword(encodedPassword);
      userRepository.save(userEntity);

      boolean successedSendMail = mailService.sendPasswordEmail(temporaryPassword, userEmail);
		  if (!successedSendMail) return ResponseDto.setFailed("");

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed(null);
    }

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
  }

}
