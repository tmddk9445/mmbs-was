package com.mong.mmbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.common.constant.ApiMappingPattern;
import com.mong.mmbs.dto.request.auth.SendPasswordEmailRequestDto;
import com.mong.mmbs.dto.request.auth.SignUpRequestDto;
import com.mong.mmbs.dto.request.auth.resetPasswordPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.auth.SignInGetResponseDto;
import com.mong.mmbs.dto.response.auth.SignUpPostResponseDto;
import com.mong.mmbs.dto.response.auth.FindIdGetResponseDto;
import com.mong.mmbs.dto.response.auth.FindPasswordGetResponseDto;
import com.mong.mmbs.dto.response.auth.ResetPasswordPostResponseDto;
import com.mong.mmbs.service.AuthService;
import com.mong.mmbs.service.MailService;
import com.mong.mmbs.service.UserService;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
public class AuthController {

	@Autowired AuthService authService;
	@Autowired UserService userUpdateService;
	@Autowired MailService mailService;

	private static final String POST_SIGN_UP = "/signUp";
	private static final String POST_RESET_PASSWORD = "/resetPassword";

	private static final String GET_FIND_ID = "/findId";
	private static final String GET_FIND_PASSWORD = "/findPassword";
	private static final String GET_SIGN_IN = "/signIn";

	private static final String POST_SEND_PASSWORD_EMAIL = "/sendPassword/{userEmail}";

	@PostMapping(POST_SIGN_UP)
	public ResponseDto<SignUpPostResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestBody) {
		ResponseDto<SignUpPostResponseDto> response = authService.signUp(requestBody);
		return response;
	}

	@PostMapping(POST_RESET_PASSWORD)
	public ResponseDto<ResetPasswordPostResponseDto> resetPassword(
			@Valid @RequestBody resetPasswordPostRequestDto requestBody) {
		ResponseDto<ResetPasswordPostResponseDto> response = authService.resetPassword(requestBody);
		return response;
	}

	@GetMapping(GET_FIND_ID)
	public ResponseDto<FindIdGetResponseDto> findId(@PathVariable("userEmail") String userEmail,
			@PathVariable("userName") String userName) {
		ResponseDto<FindIdGetResponseDto> response = authService.findId(userEmail, userName);
		return response;
	}

	@GetMapping(GET_FIND_PASSWORD)
	public ResponseDto<FindPasswordGetResponseDto> findPassword(@PathVariable("userId") String userId,
			@PathVariable("userName") String userName, @PathVariable("userEmail") String userEmail) {
		ResponseDto<FindPasswordGetResponseDto> response = authService.findPassword(userId, userName, userEmail);
		return response;
	}

	@GetMapping(GET_SIGN_IN)
	public ResponseDto<SignInGetResponseDto> signIn(@PathVariable("userId") String userId, @PathVariable("userPassword") String userPassword) {
		ResponseDto<SignInGetResponseDto> response = authService.signIn(userId, userPassword);
		return response;
	}

	@PostMapping(POST_SEND_PASSWORD_EMAIL)
	public ResponseDto<Boolean> sendPasswordEmail(@RequestBody SendPasswordEmailRequestDto requestBody) {
		ResponseDto<Boolean> response = authService.sendPasswordEmail(requestBody);
		return response;
	}

}
