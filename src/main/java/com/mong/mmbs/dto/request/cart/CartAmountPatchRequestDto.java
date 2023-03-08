package com.mong.mmbs.dto.request.cart;

import java.util.List;
import com.mong.mmbs.entity.CartEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartAmountPatchRequestDto {
	private List<CartEntity> cartList;
}
