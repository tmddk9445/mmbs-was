package com.mong.mmbs.dto.response.auth;

import com.mong.mmbs.entity.RecommendEntity;
import com.mong.mmbs.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpPostResponseDto {
    UserEntity user;
    RecommendEntity recommend;
}
