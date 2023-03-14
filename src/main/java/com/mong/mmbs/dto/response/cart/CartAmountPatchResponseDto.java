package com.mong.mmbs.dto.response.cart;

import java.util.List;

import com.mong.mmbs.entity.CartEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartAmountPatchResponseDto {
    List<CartEntity> cartList;

    
}
