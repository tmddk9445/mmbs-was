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
import com.mong.mmbs.dto.MailDto;
import com.mong.mmbs.dto.request.auth.SignUpRequestDto;
import com.mong.mmbs.dto.request.auth.resetPasswordPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.auth.SignInGetResponseDto;
import com.mong.mmbs.dto.response.auth.SignUpPostResponseDto;
import com.mong.mmbs.dto.response.auth.findIdGetResponseDto;
import com.mong.mmbs.dto.response.auth.findPasswordGetResponseDto;
import com.mong.mmbs.dto.response.auth.resetPasswordPostResponseDto;
import com.mong.mmbs.service.AuthService;
import com.mong.mmbs.service.MailService;
import com.mong.mmbs.service.MemberService;
import com.mong.mmbs.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@Slf4j
public class AuthController {
	
	@Autowired AuthService authService;
	@Autowired UserService userUpdateService;
	@Autowired MemberService memberService;
	@Autowired MailService mailService;

  private static final String POST_SIGN_UP = "/signUp";
	private static final String POST_RESET_PASSWORD = "/resetPassword";

  private static final String GET_FIND_ID = "/findId";
  private static final String GET_FIND_PASSWORD = "/findPassword";
  private static final String GET_SIGN_IN = "/signIn";
	
	@PostMapping(POST_SIGN_UP)
	public ResponseDto<SignUpPostResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestBody) {
		ResponseDto<SignUpPostResponseDto> result = authService.signUp(requestBody);
		return result;
	}

	@PostMapping(POST_RESET_PASSWORD)
	public ResponseDto<resetPasswordPostResponseDto> resetPassword(@Valid @RequestBody resetPasswordPostRequestDto requestBody){
		ResponseDto<resetPasswordPostResponseDto> result = authService.resetPassword(requestBody);
		return result;
	}

	@GetMapping(GET_FIND_ID)
	public ResponseDto<findIdGetResponseDto> findId(@PathVariable("userEmail") String userEmail, @PathVariable("userName") String userName){
		ResponseDto<findIdGetResponseDto> result = authService.findId(userEmail, userName);
		return result;
	}

	@GetMapping(GET_FIND_PASSWORD)
	public ResponseDto<findPasswordGetResponseDto> findPassword(@PathVariable("userId")String userId, @PathVariable("userName") String userName, @PathVariable("userEmail") String userEmail){
		ResponseDto<findPasswordGetResponseDto> result = authService.findPassword(userId, userName, userEmail);
		return result;
	}

	@GetMapping(GET_SIGN_IN)
	public ResponseDto<SignInGetResponseDto> signIn(@PathVariable("userId")String userId, @PathVariable("userPassword")String userPassword) {
		ResponseDto<SignInGetResponseDto> result = authService.signIn(userId, userPassword);
		return result;
	}

	/** 이메일이 DB에 존재하는지 확인 **/
    @GetMapping("/checkEmail/{userEmail}")
    public boolean checkEmail(@PathVariable("userEmail") String userEmail){

      log.info("checkEmail 진입");
      System.out.println(userEmail);
        return memberService.checkEmail(userEmail);
    }
    
    /** 비밀번호 찾기 - 임시 비밀번호 발급 **/
    @GetMapping("/sendPwd/{userEmail}")
    public ResponseDto<?> sendPwdEmail(@PathVariable("userEmail") String userEmail) {

      log.info("sendPwdEmail 진입");
      log.info("이메일 : "+ userEmail);

        /** 임시 비밀번호 생성 **/
        String tmpPassword = memberService.getTmpPassword();

        /** 임시 비밀번호 저장 **/
        boolean result = memberService.updatePassword(tmpPassword, userEmail);
        if (!result) return ResponseDto.setFailed("failed");

        /** 메일 생성 & 전송 **/
        MailDto mail = mailService.createMail(tmpPassword, userEmail);
        mailService.sendMail(mail);

        log.info("임시 비밀번호 전송 완료");

        return ResponseDto.setSuccess("success", tmpPassword);
    }
	
}
