package com.mong.mmbs.dto.response.order;

import com.mong.mmbs.entity.OrderDetailEntity;
import com.mong.mmbs.entity.OrderEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderPostResponseDto {
    
    OrderEntity orderEntity;
    OrderDetailEntity orderDetailEntity;

    public OrderPostResponseDto(OrderEntity orderEntity, OrderDetailEntity orderDetailEntity) {
	
        this.orderEntity = orderEntity;
        this.orderDetailEntity = orderDetailEntity;
    }
    
}
