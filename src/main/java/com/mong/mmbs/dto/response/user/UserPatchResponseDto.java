package com.mong.mmbs.dto.response.user;

import com.mong.mmbs.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPatchResponseDto {
// 아이디
	private String userId;
//	비밀번호
	private String userPassword;
//	이메일
	private String userEmail;
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
//	가입 날짜
	private String userSignUpDate;
//	탈퇴 날짜
	private String userWithdraw;
	
	public UserPatchResponseDto(UserEntity userEntity) {
		
		this.userId = userEntity.getUserId();
		this.userPassword = userEntity.getUserPassword();
		this.userEmail = userEntity.getUserEmail();
		this.userAddress = userEntity.getUserAddress();
		this.userAddressDetail = userEntity.getUserAddressDetail();
		this.userName = userEntity.getUserName();
		this.userPhone = userEntity.getUserPhone();
		this.userKidBirth = userEntity.getUserKidBirth();
		this.userSignUpDate = userEntity.getUserSignUpDate();
    
	}
}
