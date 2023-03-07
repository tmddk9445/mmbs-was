package com.mong.mmbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.request.gift.GiftPatchReqeustDto;
import com.mong.mmbs.dto.request.gift.GiftPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.gift.GiftGetResponseDto;
import com.mong.mmbs.dto.response.gift.GiftPostResponseDto;
import com.mong.mmbs.service.GiftService;

@RequestMapping("/apis/auth")
@RestController
public class GiftController {

	@Autowired GiftService giftService;

	@PostMapping("/")
	public ResponseDto<GiftPostResponseDto> post(@Valid @RequestBody GiftPostRequestDto requestBody) {
		ResponseDto<GiftPostResponseDto> response = giftService.post(requestBody);
		return response;
	}

	@GetMapping("/")
	public ResponseDto<GiftGetResponseDto> get(@PathVariable("giftCode") int giftCode){
		ResponseDto<GiftGetResponseDto> response = giftService.get(giftCode);
		return response;
	}

	@PatchMapping("/")
	public ResponseDto<?> giftorder(@RequestBody GiftPatchReqeustDto requsetBody){
		return giftService.giftorder(requsetBody);
	}
	

}
