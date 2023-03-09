package com.mong.mmbs.dto.response.order;

import java.util.List;

import com.mong.mmbs.entity.OrderDetailEntity;
import com.mong.mmbs.entity.OrderEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGetListResponseDto {

  private OrderEntity orderEntity;
  private List<OrderDetailEntity> orderDetailList;

}