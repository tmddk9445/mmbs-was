package com.mong.mmbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.user.UserDeleteResponseDto;
import com.mong.mmbs.dto.response.user.UserGetResponseDto;
import com.mong.mmbs.dto.response.user.UserPatchResponseDto;
import com.mong.mmbs.common.constant.ApiMappingPattern;
import com.mong.mmbs.dto.request.user.UserPatchRequestDto;
import com.mong.mmbs.service.UserService;

@RestController
@RequestMapping(ApiMappingPattern.USER)
public class UserController {
	
	@Autowired UserService userService;

	private static final String GET_USER = "/";
	private static final String PATCH_USER_UPDATE = "/";
	private static final String DELETE_USER = "/{userEmail}";
	
	@GetMapping(GET_USER)
	public ResponseDto<UserGetResponseDto> getUser(@AuthenticationPrincipal String userId) {
		ResponseDto<UserGetResponseDto> response = userService.getUser(userId);
		return response;
	}

	@PatchMapping(PATCH_USER_UPDATE)
	public ResponseDto<UserPatchResponseDto> patchUser(@AuthenticationPrincipal String userId, @Valid @RequestBody UserPatchRequestDto dto) {
		ResponseDto<UserPatchResponseDto> response = userService.patchUser(userId, dto);
		return response;
	}
	
	@DeleteMapping(DELETE_USER)
	public ResponseDto<UserDeleteResponseDto> deleteUser(@AuthenticationPrincipal String userId, @PathVariable("userEmail") String userEmail) {
		ResponseDto<UserDeleteResponseDto> response = userService.deleteUser(userId, userEmail);
		return response;
	} 

}
