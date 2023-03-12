package com.mong.mmbs.dto.response.order;

import com.mong.mmbs.dto.request.order.GiftPatchReqeustDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftPatchResponseDto {

// 주문번호
  private String orderNumber;
//  사은품
  private int orderGiftCode;


  public GiftPatchResponseDto(GiftPatchReqeustDto dto) {

    this.orderNumber = dto.getOrderNumber();
    this.orderGiftCode = dto.getOrderGiftCode();

  }

}
