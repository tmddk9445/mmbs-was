package com.mong.mmbs.dto.response.auth;

import com.mong.mmbs.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class findPasswordGetResponseDto {
  
  private String userPassword;

  public findPasswordGetResponseDto(UserEntity userEntity) {
		this.userPassword = userEntity.getUserId();
		
	}
}
