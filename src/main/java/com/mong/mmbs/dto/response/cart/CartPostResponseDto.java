package com.mong.mmbs.dto.response.cart;

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
}
