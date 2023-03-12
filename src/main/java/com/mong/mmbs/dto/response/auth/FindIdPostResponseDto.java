package com.mong.mmbs.dto.response.auth;

import com.mong.mmbs.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindIdPostResponseDto {
  
  private String userId;

  public FindIdPostResponseDto(UserEntity userEntity) {
		this.userId = userEntity.getUserId();
		
	}
}
