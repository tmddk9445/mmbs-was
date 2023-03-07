package com.mong.mmbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		String cartUserId = dto.getCartUserId();
		int cartProductId = dto.getCartProductId();
		int cartProductAmount = dto.getCartProductAmount();

		ProductEntity productEntity = null;
		CartEntity cartEntity = null;
		List<CartEntity> cartList = new ArrayList<CartEntity>();
    
		try {

			productEntity = productRepository.findByProductSeq(cartProductId);
			if (productEntity == null) return ResponseDto.setFailed("실패");

			cartEntity = cartRepository.findByCartUserIdAndCartProductId(cartUserId, cartProductId);

			cartList = cartRepository.findByCartUserId(cartUserId);

		} catch (Exception exception) {
			return ResponseDto.setFailed("데이터베이스 오류");
		}

		if (cartEntity == null) {

			cartEntity = new CartEntity(dto, productEntity);

			try {

				cartRepository.save(cartEntity);

			} catch (Exception exception) {
				return ResponseDto.setFailed("error");
			}

		} else {

			cartEntity.setCartProductAmount(cartProductAmount);

			try {

				cartRepository.save(cartEntity);

			} catch (Exception exception) {
				return ResponseDto.setFailed("실패");
			}

		}

		CartPostResponseDto data = new CartPostResponseDto();
		return ResponseDto.setSuccess("성공", data);

	}

	public ResponseDto<CartGetResponseDto> get(String userId) {

		List<CartEntity> cartList = new ArrayList<CartEntity>();

		try {

			cartList = cartRepository.findByCartUserId(userId);
			if (cartList == null) return ResponseDto.setFailed("장바구니에 담긴 상품이 없습니다.");

		} catch (Exception exception) {

		}
		
		CartGetResponseDto data = new CartGetResponseDto(cartList);
		return ResponseDto.setSuccess("성공", data);

	}


	public ResponseDto<CartPatchResponseDto> amount(AmountUpdateDto dto) {

		List<CartEntity>cartEntity = dto.getSelectCartList();
		cartRepository.saveAll(cartEntity);

		CartPatchResponseDto data = new CartPatchResponseDto();
		return ResponseDto.setSuccess("장바구니에서 수정됬었습니다 .", data);
	}

	public ResponseDto<CartPatchAllResponseDto> allAmount(DeleteAllFromCartDto dto) {

		String cartUserId = dto.getCartUserId();

		List<CartEntity> cartEntity = cartRepository.findByCartUserId(cartUserId);
		int total = 0;
		if (cartEntity != null) {
			for (CartEntity cartEntity2 : cartEntity) {
				total += cartEntity2.getCartProductAmount();
			}
		}

		CartPatchAllResponseDto data = new CartPatchAllResponseDto();
		return ResponseDto.setSuccess("장바구니에 담긴 책의 총수량", data); // total
	}

	public ResponseDto<CartDeleteResponseDto> delete(String userId, int cartId) {

		CartEntity cartEntity = null;
		try {
			cartEntity = cartRepository.findByCartId(cartId);
			if (cartEntity == null) return ResponseDto.setFailed("Does not Exist Cart");
			cartRepository.delete(cartEntity);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		
		List<CartEntity> cartList = new ArrayList<CartEntity>();
		
		try {
			cartList = cartRepository.findByCartUserId(userId);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		
		CartDeleteResponseDto data = new CartDeleteResponseDto(cartList);
		return ResponseDto.setSuccess("장바구니에서 삭제되었습니다 .", data);
	}

	public ResponseDto<CartDeleteAllResponseDto> deleteAll(String cartUserId) {

		List<CartEntity> cartEntity = cartRepository.findByCartUserId(cartUserId);
		if (cartEntity != null)
			cartRepository.deleteAll(cartEntity);
		return ResponseDto.setSuccess("장바구니에서 전부 삭제되었습니다 .", null);
	}

}
