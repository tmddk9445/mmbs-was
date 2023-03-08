package com.mong.mmbs.dto.response.auth;

import com.mong.mmbs.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class findIdGetResponseDto {
  
  private String userId;

  public findIdGetResponseDto(UserEntity userEntity) {
		this.userId = userEntity.getUserId();
		
	}
}
