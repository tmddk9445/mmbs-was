package com.mong.mmbs.dto.request.cart;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartPostRequestDto {

	@NotNull
	private int cartProductId;
	@NotNull
	private int cartProductAmount;

}