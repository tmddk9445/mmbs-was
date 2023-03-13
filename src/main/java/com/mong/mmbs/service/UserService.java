package com.mong.mmbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.request.user.UserPatchRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.user.UserDeleteResponseDto;
import com.mong.mmbs.dto.response.user.UserGetResponseDto;
import com.mong.mmbs.dto.response.user.UserPatchResponseDto;
import com.mong.mmbs.entity.UserEntity;
import com.mong.mmbs.repository.UserRepository;

@Service
public class UserService {

  @Autowired UserRepository userRepository;

  public ResponseDto<UserGetResponseDto> getUser(String userId) {

    UserGetResponseDto data = null;

    try {

      UserEntity userEntity = userRepository.findByUserId(userId);

      if (userEntity != null)
        userEntity.setUserPassword(ResponseMessage.NULL);

        data = new UserGetResponseDto(userEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }

    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

  }

  public ResponseDto<UserPatchResponseDto> patchUser(String userId, UserPatchRequestDto dto) {

    UserPatchResponseDto data = null;

    try {

      UserEntity userEntity = userRepository.findByUserId(userId);
      if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

      userEntity.setPatchUser(dto);
      userRepository.save(userEntity);

      data = new UserPatchResponseDto(userEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }

    return ResponseDto.setSuccess("Sucess", data);

  }

  public ResponseDto<UserDeleteResponseDto> deleteUser(String userId, String userEmail) {

    UserDeleteResponseDto data = null;

    try {

      if (!userRepository.existsByUserIdAndUserEmail(userId, userEmail))
        return ResponseDto.setFailed("UserId Or UserEmail Does Not Exist");

      UserEntity userEntity = userRepository.findByUserId(userId);
      userRepository.delete(userEntity);

      data = new UserDeleteResponseDto();

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed("Failed");
    }

    return ResponseDto.setSuccess("Success", data);

  }
}
