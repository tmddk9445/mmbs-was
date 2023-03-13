package com.mong.mmbs.dto.response.user;

import com.mong.mmbs.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetResponseDto {

// 아이디
	private String userId;
//	주소
	private String userAddress;
//	상세 주소
	private String userAddressDetail;
//	이름
	private String userName;
//	전화번호
	private String userPhone;
//	아이 생일
	private String userKidBirth;

  
  public UserGetResponseDto(UserEntity userEntity) {
		
		this.userId = userEntity.getUserId();
		this.userAddress = userEntity.getUserAddress();
		this.userAddressDetail =userEntity.getUserAddressDetail();
		this.userName = userEntity.getUserName();
		this.userPhone = userEntity.getUserPhone();
		this.userKidBirth = userEntity.getUserKidBirth();
		
	}
}
