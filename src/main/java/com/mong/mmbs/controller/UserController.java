package com.mong.mmbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.UserUpdateDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.common.constant.ApiMappingPattern;
import com.mong.mmbs.dto.UserDeleteDto;

import com.mong.mmbs.service.UserService;

@RestController
@RequestMapping(ApiMappingPattern.USER)
public class UserController {
	
	@Autowired UserService userService;

	private static final String GET_USER = "/";
	private static final String PATCH_USER_UPDATE = "/";
	private static final String DELETE_USER = "/{userEmail}";
	
	@GetMapping(GET_USER)
	public ResponseDto<?> getUser(@AuthenticationPrincipal String userId) {
		return userService.getUser(userId);
	}

	@PatchMapping(PATCH_USER_UPDATE)
	public ResponseDto<?> patchUser(@Valid @RequestBody UserUpdateDto dto) {
		return userService.patchUser(dto);
	}
	
	@DeleteMapping(DELETE_USER)
	public ResponseDto<?> deleteUser(@AuthenticationPrincipal String userId, @PathVariable("userEmail") String userEmail) {
		return userService.deleteUser(userId, dto);
	} 

}
