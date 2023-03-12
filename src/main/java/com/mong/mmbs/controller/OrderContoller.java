package com.mong.mmbs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.common.constant.ApiMappingPattern;
import com.mong.mmbs.dto.request.order.OrderPostRequestDto;
import com.mong.mmbs.dto.request.order.GiftPatchReqeustDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.order.GiftGetResponseDto;
import com.mong.mmbs.dto.response.order.GiftPatchResponseDto;
import com.mong.mmbs.dto.response.order.OrderGetListResponseDto;
import com.mong.mmbs.dto.response.order.OrderPostResponseDto;
import com.mong.mmbs.service.OrderService;

@RestController
@RequestMapping(ApiMappingPattern.ORDER)
public class OrderContoller {

  @Autowired OrderService orderService;

  public static final String ORDER_POST = "/"; 

  public static final String ORDER_GET_LIST = "/list"; 
  public static final String GIFT_GET_GIFTCODE = "/{giftCode}";
	
	public static final String GIFT_PATCH = "/gift";
  
  @PostMapping(ORDER_POST)
  public ResponseDto<OrderPostResponseDto> postOrder(@Valid @RequestBody OrderPostRequestDto requestBody){
    ResponseDto<OrderPostResponseDto> result = orderService.postOrder(requestBody);
    return result;
  }

  @GetMapping(ORDER_GET_LIST)
	public ResponseDto<List<OrderGetListResponseDto>> getOrderList(@AuthenticationPrincipal String userId) { 
		ResponseDto<List<OrderGetListResponseDto>> result = orderService.getOrderList(userId);
		return result;
	}
  
  @GetMapping(GIFT_GET_GIFTCODE)
	public ResponseDto<GiftGetResponseDto> getGiftCode(@PathVariable("giftCode") int giftCode){
		ResponseDto<GiftGetResponseDto> response = orderService.getGiftCode(giftCode);
		return response;
	}

	@PatchMapping(GIFT_PATCH)
	public ResponseDto<GiftPatchResponseDto> patchGift(@Valid @RequestBody GiftPatchReqeustDto requsetBody){
		ResponseDto<GiftPatchResponseDto> response = orderService.patchGift(requsetBody);
		return response;
	}

}
