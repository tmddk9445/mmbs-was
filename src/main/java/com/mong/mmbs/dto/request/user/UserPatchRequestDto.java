package com.mong.mmbs.dto.request.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPatchRequestDto {

//	주소
  @NotBlank
	private String userAddress;
//	상세 주소
	@NotBlank
	private String userAddressDetail;
//	이름
	@NotBlank
	private String userName;
//	전화번호
	@NotBlank
	private String userPhone;
//	아이 생일
	private String userKidBirth;

}
