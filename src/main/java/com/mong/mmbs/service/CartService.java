package com.mong.mmbs.service;

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

	public ResponseDto<CartPostResponseDto> post(String userId, CartPostRequestDto dto) {

		CartPostResponseDto data = null;

		String cartUserId = userId;
		int cartProductId = dto.getCartProductId();
		int cartProductAmount = dto.getCartProductAmount();

		try {

			ProductEntity productEntity = productRepository.findByProductSeq(cartProductId);
			if (productEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_PRODUCT);

			CartEntity cartEntity = cartRepository.findByCartUserIdAndCartProductId(cartUserId, cartProductId);

      if (cartEntity == null) {

        try {
          cartEntity = new CartEntity(userId, dto, productEntity);
          cartRepository.save(cartEntity);
  
        } catch (Exception exception) {
          exception.printStackTrace();
          return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
  
      } else {
  
        try {
          
          cartEntity.setCartProductAmount(cartProductAmount);
          cartRepository.save(cartEntity);
  
        } catch (Exception exception) {
          exception.printStackTrace();
          return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
  
      }
  
      data = new CartPostResponseDto(cartEntity, productEntity);

		} catch (Exception exception) {
      exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

	public ResponseDto<CartGetResponseDto> get(String userId) {

		CartGetResponseDto data = null;

		try {

			List<CartEntity> cartList = cartRepository.findByCartUserId(userId);
			if (cartList == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CARTLIST);
			
			data = new CartGetResponseDto(cartList);

		} catch (Exception exception) {
      exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}
		
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

	public ResponseDto<CartAmountPatchResponseDto> amount(CartAmountPatchRequestDto dto) {

		CartAmountPatchResponseDto data = null;

		try {

			List<CartEntity> cartList = dto.getCartList();
			cartRepository.saveAll(cartList);
	
			data = new CartAmountPatchResponseDto(cartList);

		} catch (Exception exception) {
      exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}
		
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
	}

	public ResponseDto<CartDeleteResponseDto> delete(String userId, int cartId) {

    CartDeleteResponseDto data = null;

		try {

			CartEntity cartEntity = cartRepository.findByCartId(cartId);
			if (cartEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CART);

			cartRepository.delete(cartEntity);

			List<CartEntity> cartList = cartRepository.findByCartUserId(userId);
      data = new CartDeleteResponseDto(cartList);

		} catch (Exception exception) {
      exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}
		
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);	
		
	}

	public ResponseDto<CartDeleteAllResponseDto> deleteAll(String cartUserId) {

    CartDeleteAllResponseDto data = null;

		try {

			List<CartEntity> cartList = cartRepository.findByCartUserId(cartUserId);
			if (cartList == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CART);

			cartRepository.deleteAll(cartList);

			cartList = cartRepository.findByCartUserId(cartUserId);

			data = new CartDeleteAllResponseDto(cartList);

		} catch (Exception exception) {
      exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}
		
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
	}

}
