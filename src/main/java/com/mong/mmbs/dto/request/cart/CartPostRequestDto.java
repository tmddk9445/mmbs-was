package com.mong.mmbs.dto.request.cart;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartPostRequestDto {

	@NotBlank
	private String cartUserId;
	@NotBlank
	private int cartProductId;
	@NotBlank
	private int cartProductAmount;

	// public CartPostRequestDto(ProductEntity productEntity){
	// 	private int productSeq;
	// 	private String productTitle;
	// 	private String productImageUrl;
	// 	private int productPrice;
	// }

	// @NotBlank
	// ProductEntity productEntity;
}