package com.mong.mmbs.dto.response.order;

import com.mong.mmbs.entity.GiftEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftPatchResponseDto {

    private int giftCode;
    private String giftName;
    private String giftImage;

    public GiftPatchResponseDto(GiftEntity giftEntity){

		this.giftCode = giftEntity.getGiftCode();
		this.giftName = giftEntity.getGiftName();
		this.giftImage = giftEntity.getGiftImage();
    
	}

}
