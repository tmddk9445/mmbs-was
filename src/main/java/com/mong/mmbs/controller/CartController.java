package com.mong.mmbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.request.cart.CartAmountPatchRequestDto;
import com.mong.mmbs.dto.request.cart.CartPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.cart.CartDeleteAllResponseDto;
import com.mong.mmbs.dto.response.cart.CartDeleteResponseDto;
import com.mong.mmbs.dto.response.cart.CartGetResponseDto;
import com.mong.mmbs.dto.response.cart.CartAmountPatchResponseDto;
import com.mong.mmbs.dto.response.cart.CartPostResponseDto;
import com.mong.mmbs.service.CartService;
import com.mong.mmbs.common.constant.ApiMappingPattern;

@RestController
@RequestMapping(ApiMappingPattern.CART)
public class CartController {

	@Autowired
	CartService cartService;

	public static final String CART_POST = "/";
    
    public static final String CART_GET = "/";

    public static final String CART_PATCH_AMOUNT = "/";

    public static final String CART_DELETE_CARTID = "/{cartId}";
    public static final String CART_DELETE_CARTUSERID = "/all/{cartUserId}";

	@PostMapping(CART_POST)
	public ResponseDto<CartPostResponseDto> post(@AuthenticationPrincipal String userId, @Valid @RequestBody CartPostRequestDto requestBody){
		ResponseDto<CartPostResponseDto> result = cartService.post(userId, requestBody);
		return result;
	}

	@GetMapping(CART_GET)
	public ResponseDto<CartGetResponseDto> get(@AuthenticationPrincipal String userId){
		ResponseDto<CartGetResponseDto> result = cartService.get(userId);
		return result;
	}

	@PatchMapping(CART_PATCH_AMOUNT)
	public ResponseDto<CartAmountPatchResponseDto> amount(@Valid @RequestBody CartAmountPatchRequestDto requestBody){
		ResponseDto<CartAmountPatchResponseDto> result = cartService.amount(requestBody);
		return result;
	}

	@DeleteMapping(CART_DELETE_CARTID)
	public ResponseDto<CartDeleteResponseDto> delete(@AuthenticationPrincipal String userId, @PathVariable("cartId") int cartId){
		ResponseDto<CartDeleteResponseDto> result = cartService.delete(userId, cartId);
		return result;
	}
	
	@DeleteMapping(CART_DELETE_CARTUSERID)
	public ResponseDto<CartDeleteAllResponseDto> deleteAll(@PathVariable("cartUserId") String cartUserId){
		ResponseDto<CartDeleteAllResponseDto> result = cartService.deleteAll(cartUserId);
		return result;
	}
}
