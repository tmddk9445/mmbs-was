package com.mong.mmbs.dto.response.auth;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mong.mmbs.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class resetPasswordPostResponseDto {
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

  public resetPasswordPostResponseDto(UserEntity userEntity) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		this.userId = userEntity.getUserId();
		this.userPassword = userEntity.getUserPassword();
		this.userEmail = userEntity.getUserEmail();
		this.userAddress = userEntity.getUserAddress();
		this.userAddressDetail =userEntity.getUserAddressDetail();
		this.userName = userEntity.getUserName();
		this.userPhone = userEntity.getUserPhone();
		this.userKidBirth = userEntity.getUserKidBirth();
		this.userSignUpDate = dateFormat.format(new Date());
    
	}

}
