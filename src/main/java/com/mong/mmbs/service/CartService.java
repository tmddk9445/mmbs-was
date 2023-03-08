package com.mong.mmbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.request.cart.CartAmountPatchRequestDto;
import com.mong.mmbs.dto.request.cart.CartPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.cart.CartDeleteAllResponseDto;
import com.mong.mmbs.dto.response.cart.CartDeleteResponseDto;
import com.mong.mmbs.dto.response.cart.CartGetResponseDto;
import com.mong.mmbs.dto.response.cart.CartAmountPatchResponseDto;
import com.mong.mmbs.dto.response.cart.CartPostResponseDto;
import com.mong.mmbs.entity.CartEntity;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.CartRepository;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;

	public ResponseDto<CartPostResponseDto> post(CartPostRequestDto dto) {

		CartPostResponseDto data = null;

		String cartUserId = dto.getCartUserId();
		int cartProductId = dto.getCartProductId();
		int cartProductAmount = dto.getCartProductAmount();

		ProductEntity productEntity = null;
		CartEntity cartEntity = null;
    
		try {

			productEntity = productRepository.findByProductSeq(cartProductId);
			if (productEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PRODUCT);

			cartEntity = cartRepository.findByCartUserIdAndCartProductId(cartUserId, cartProductId);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		if (cartEntity == null) {

			try {
				cartEntity = new CartEntity(dto, productEntity);
				cartRepository.save(cartEntity);

			} catch (Exception exception) {
				return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
			}

		} else {

			try {
        
				cartEntity.setCartProductAmount(cartProductAmount);
				cartRepository.save(cartEntity);

			} catch (Exception exception) {
				return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
			}

		}

		data = new CartPostResponseDto(cartEntity, productEntity);
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

	public ResponseDto<CartGetResponseDto> get(String userId) {

		CartGetResponseDto data = null;

		List<CartEntity> cartList = new ArrayList<CartEntity>();

		try {

			cartList = cartRepository.findByCartUserId(userId);
			if (cartList == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CARTLIST);
			
			data = new CartGetResponseDto(cartList);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}
		
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

	public ResponseDto<CartAmountPatchResponseDto> amount(CartAmountPatchRequestDto dto) {

		CartAmountPatchResponseDto data = null;

		List<CartEntity> cartList = new ArrayList<CartEntity>();

		try {

			cartList = dto.getCartList();
			cartRepository.saveAll(cartList);
	
			data = new CartAmountPatchResponseDto();

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}
		
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
	}

	public ResponseDto<CartDeleteResponseDto> delete(String userId, int cartId) {

    CartDeleteResponseDto data = null;

		CartEntity cartEntity = null;
		List<CartEntity> cartList = new ArrayList<CartEntity>();

		try {

			cartEntity = cartRepository.findByCartId(cartId);
			if (cartEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CART);

			cartList = cartRepository.findByCartUserId(userId);

      data = new CartDeleteResponseDto(cartList);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}
		
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);	
		
	}

	public ResponseDto<CartDeleteAllResponseDto> deleteAll(String cartUserId) {

    CartDeleteAllResponseDto data = null;

		List<CartEntity> cartList = new ArrayList<CartEntity>();
		
		try {

			cartList = cartRepository.findByCartUserId(cartUserId);
			if (cartList != null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CART);

			cartRepository.deleteAll(cartList);

			data = new CartDeleteAllResponseDto(cartList);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}
		
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
	}

}
