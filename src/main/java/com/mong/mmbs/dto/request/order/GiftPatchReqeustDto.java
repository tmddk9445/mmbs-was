package com.mong.mmbs.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftPatchReqeustDto {

	private int orderGiftCode; 
	private String orderNumber;
	
}
