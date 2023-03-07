package com.mong.mmbs.dto.request.gift;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GiftPostRequestDto {

    @NotBlank
    private int giftCode;
    @NotBlank
    private String giftName;
    @NotBlank
    private String giftImage;

}
