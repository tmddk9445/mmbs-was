package com.mong.mmbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			if (productEntity == null) return ResponseDto.setFailed("Does Not Exist Product");

			cartEntity = cartRepository.findByCartUserIdAndCartProductId(cartUserId, cartProductId);

		} catch (Exception exception) {
			return ResponseDto.setFailed("데이터베이스 오류");
		}

		if (cartEntity == null) {

			try {
				cartEntity = new CartEntity(dto, productEntity);
				cartRepository.save(cartEntity);

			} catch (Exception exception) {
				return ResponseDto.setFailed("error");
			}

		} else {

			try {
        
				cartEntity.setCartProductAmount(cartProductAmount);
				cartRepository.save(cartEntity);

			} catch (Exception exception) {
				return ResponseDto.setFailed("실패");
			}

		}

		data = new CartPostResponseDto(cartEntity, productEntity);
		return ResponseDto.setSuccess("성공", data);

	}

	public ResponseDto<CartGetResponseDto> get(String userId) {

		CartGetResponseDto data = null;

		List<CartEntity> cartList = new ArrayList<CartEntity>();

		try {

			cartList = cartRepository.findByCartUserId(userId);
			if (cartList == null) return ResponseDto.setFailed("장바구니에 담긴 상품이 없습니다.");
			
			data = new CartGetResponseDto(cartList);

		} catch (Exception exception) {
			return ResponseDto.setFailed("실패");
		}
		
		return ResponseDto.setSuccess("성공", data);

	}

	public ResponseDto<CartAmountPatchResponseDto> amount(CartAmountPatchRequestDto dto) {

		CartAmountPatchResponseDto data = null;

		List<CartEntity> cartList = new ArrayList<CartEntity>();

		try {

			cartList = dto.getCartList();
			cartRepository.saveAll(cartList);
	
			data = new CartAmountPatchResponseDto();

		} catch (Exception exception) {
			return ResponseDto.setFailed("실패");
		}
		
		return ResponseDto.setSuccess("장바구니에서 수정되었습니다.", data);
	}

	public ResponseDto<CartDeleteResponseDto> delete(String userId, int cartId) {

    CartDeleteResponseDto data = null;

		CartEntity cartEntity = null;
		List<CartEntity> cartList = new ArrayList<CartEntity>();

		try {

			cartEntity = cartRepository.findByCartId(cartId);
			if (cartEntity == null) return ResponseDto.setFailed("Does not Exist Cart");

			cartList = cartRepository.findByCartUserId(userId);

      data = new CartDeleteResponseDto(cartList);

		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		
		return ResponseDto.setSuccess("장바구니에서 삭제되었습니다.", data);	
		
	}

	public ResponseDto<CartDeleteAllResponseDto> deleteAll(String cartUserId) {

    CartDeleteAllResponseDto data = null;

		List<CartEntity> cartEntity = null;
		
		try {

			cartEntity = cartRepository.findByCartUserId(cartUserId);
			if (cartEntity != null) return ResponseDto.setFailed("Does not Exist Cart");

			cartRepository.deleteAll(cartEntity);

      data = new CartDeleteAllResponseDto(cartEntity);

		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		
		return ResponseDto.setSuccess("장바구니에서 전부 삭제되었습니다.", data);
	}

}
