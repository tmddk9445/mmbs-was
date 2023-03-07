package com.mong.mmbs.dto.response.cart;

import java.util.List;

import com.mong.mmbs.entity.CartEntity;
import com.mong.mmbs.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartPostResponseDto {
    // 장바구니 시퀀스
    private int cartId;
//  유저 아이디
	private String cartUserId;
//  제품 아이디
	private int cartProductId;
//  제품 이름
	private String cartProductName;
//  제품 이미지
	private String cartProductImage;
//  제품 개당 가격
	private int cartProductPrice;
//  제품 개수
	private int cartProductAmount;

	public CartPostResponseDto(CartEntity cartEntity, ProductEntity product) {
		this.cartId = cartEntity.getCartId();
		this.cartUserId = cartEntity.getCartUserId();
		this.cartProductId = product.getProductSeq();
		this.cartProductName = product.getProductTitle();
		this.cartProductImage = product.getProductImageUrl();
		this.cartProductPrice = product.getProductPrice();
		this.cartProductAmount = cartEntity.getCartProductAmount();
	}
	
}
