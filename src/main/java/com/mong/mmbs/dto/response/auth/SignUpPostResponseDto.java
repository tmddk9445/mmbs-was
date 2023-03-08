package com.mong.mmbs.dto.response.auth;

import com.mong.mmbs.entity.RecommendEntity;
import com.mong.mmbs.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpPostResponseDto {

    UserEntity userEntity;
    RecommendEntity recommendEntity;

    public SignUpPostResponseDto(UserEntity userEntity, RecommendEntity recommendEntity) {
	
        this.userEntity = userEntity;
        this.recommendEntity = recommendEntity;
        
    }
}
