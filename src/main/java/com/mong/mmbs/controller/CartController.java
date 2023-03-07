package com.mong.mmbs.controller;

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

import com.mong.mmbs.dto.AmountUpdateDto;
import com.mong.mmbs.dto.DeleteAllFromCartDto;
import com.mong.mmbs.dto.request.cart.CartPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.cart.CartDeleteAllResponseDto;
import com.mong.mmbs.dto.response.cart.CartDeleteResponseDto;
import com.mong.mmbs.dto.response.cart.CartGetResponseDto;
import com.mong.mmbs.dto.response.cart.CartPatchAllResponseDto;
import com.mong.mmbs.dto.response.cart.CartPatchResponseDto;
import com.mong.mmbs.dto.response.cart.CartPostResponseDto;
import com.mong.mmbs.service.CartService;
import com.mong.mmbs.util.EndPoint;

@RestController
@RequestMapping(EndPoint.CART)
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping(EndPoint.CART_POST_NULL)
	public ResponseDto<CartPostResponseDto> post(@RequestBody CartPostRequestDto requestBody){
		ResponseDto<CartPostResponseDto> result = cartService.post(requestBody);
		return result;
	}

	@GetMapping(EndPoint.CART_GET_NULL)
	public ResponseDto<CartGetResponseDto> get(@AuthenticationPrincipal String userid){
		ResponseDto<CartGetResponseDto> result = cartService.get(userid);
		return result;
	}

	@PatchMapping(EndPoint.CART_PATCH_AMOUNT)
	public ResponseDto<CartPatchResponseDto> amount(@RequestBody AmountUpdateDto requestBody){
		ResponseDto<CartPatchResponseDto> result = cartService.amount(requestBody);
		return result;
	}
	
	@PatchMapping(EndPoint.CART_PATCH_AMOUNT_ALL)
	public ResponseDto<CartPatchAllResponseDto> allAmount(@RequestBody DeleteAllFromCartDto requestBody){
		ResponseDto<CartPatchAllResponseDto> result = cartService.amountAll(requestBody);
		return result;
	}

	@DeleteMapping(EndPoint.CART_DELETE_CARTID)
	public ResponseDto<CartDeleteResponseDto> delete(@AuthenticationPrincipal String userId, @PathVariable("cartId") int cartId){
		ResponseDto<CartDeleteResponseDto> result = cartService.delete(userId, cartId);
		return result;
	}
	
	@DeleteMapping(EndPoint.CART_DELETE_CARTUSERID)
	public ResponseDto<CartDeleteAllResponseDto> deleteAll(@PathVariable("cartUserId") String cartUserId){
		ResponseDto<CartDeleteAllResponseDto> result = cartService.deleteAll(cartUserId);
		return result;
	}
}
