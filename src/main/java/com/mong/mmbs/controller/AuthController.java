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
import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.request.auth.SignUpRequestDto;
import com.mong.mmbs.dto.request.auth.resetPasswordPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.auth.SignInGetResponseDto;
import com.mong.mmbs.dto.response.auth.SignUpPostResponseDto;
import com.mong.mmbs.dto.response.auth.FindIdGetResponseDto;
import com.mong.mmbs.dto.response.auth.FindPasswordGetResponseDto;
import com.mong.mmbs.dto.response.auth.ResetPasswordPostResponseDto;
import com.mong.mmbs.dto.MailDto;
import com.mong.mmbs.service.AuthService;
import com.mong.mmbs.service.MailService;
import com.mong.mmbs.service.MemberService;
import com.mong.mmbs.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@Slf4j
public class AuthController {

	@Autowired
	AuthService authService;
	@Autowired
	UserService userUpdateService;
	@Autowired
	MemberService memberService;
	@Autowired
	MailService mailService;

	private static final String POST_SIGN_UP = "/signUp";
	private static final String POST_RESET_PASSWORD = "/resetPassword";

	private static final String GET_FIND_ID = "/findId";
	private static final String GET_FIND_PASSWORD = "/findPassword";
	private static final String GET_SIGN_IN = "/signIn";

	private static final String GET_CHECK_USEREMAIL = "/check/{userEmail}";
	private static final String GET_SEND_PASSWORD_USEREMAIL = "/sendPassword/{userEmail}";

	@PostMapping(POST_SIGN_UP)
	public ResponseDto<SignUpPostResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestBody) {
		ResponseDto<SignUpPostResponseDto> result = authService.signUp(requestBody);
		return result;
	}

	@PostMapping(POST_RESET_PASSWORD)
	public ResponseDto<ResetPasswordPostResponseDto> resetPassword(
			@Valid @RequestBody resetPasswordPostRequestDto requestBody) {
		ResponseDto<ResetPasswordPostResponseDto> result = authService.resetPassword(requestBody);
		return result;
	}

	@GetMapping(GET_FIND_ID)
	public ResponseDto<FindIdGetResponseDto> findId(@PathVariable("userEmail") String userEmail,
			@PathVariable("userName") String userName) {
		ResponseDto<FindIdGetResponseDto> result = authService.findId(userEmail, userName);
		return result;
	}

	@GetMapping(GET_FIND_PASSWORD)
	public ResponseDto<FindPasswordGetResponseDto> findPassword(@PathVariable("userId") String userId,
			@PathVariable("userName") String userName, @PathVariable("userEmail") String userEmail) {
		ResponseDto<FindPasswordGetResponseDto> result = authService.findPassword(userId, userName, userEmail);
		return result;
	}

	@GetMapping(GET_SIGN_IN)
	public ResponseDto<SignInGetResponseDto> signIn(@PathVariable("userId") String userId, @PathVariable("userPassword") String userPassword) {
		ResponseDto<SignInGetResponseDto> result = authService.signIn(userId, userPassword);
		return result;
	}

	/** 이메일이 DB에 존재하는지 확인 **/
	@GetMapping(GET_CHECK_USEREMAIL)
	public ResponseDto<?> checkEmail(@PathVariable("userEmail") String userEmail) {
		ResponseDto<?> response = memberService.checkEmail(userEmail);

		log.info("checkEmail 진입");
		System.out.println(userEmail);

		return response;
	}

	/** 비밀번호 찾기 - 임시 비밀번호 발급 **/
	@GetMapping(GET_SEND_PASSWORD_USEREMAIL)
	public ResponseDto<?> sendPwdEmail(@PathVariable("userEmail") String userEmail) {

		log.info("sendPwdEmail 진입");
		log.info("이메일 : " + userEmail);

		/** 임시 비밀번호 생성 **/
		String tmpPassword = memberService.getTmpPassword();

		/** 임시 비밀번호 저장 **/
		memberService.updatePassword(tmpPassword, userEmail);

		/** 메일 생성 & 전송 **/
		MailDto mail = mailService.createMail(tmpPassword, userEmail);
		mailService.sendMail(mail);

		log.info("임시 비밀번호 전송 완료");

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);
	}

}
