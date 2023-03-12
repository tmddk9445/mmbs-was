package com.mong.mmbs.dto.request.order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPostRequestDto {

	private boolean orderUserWhether;
	private String orderGuestPassword;
	private String orderGuestPasswordCheck;
	private String orderUserId;
	private int orderGiftCode;
	
	@NotBlank
	private String orderUserName;
	@NotBlank
	private String orderUserPhone;
	@NotBlank
	private String orderUserEmail;
	@NotBlank
	private String orderRecieptName;
	@NotBlank
	private String orderRecieptPhone;
	@NotBlank
	private String orderShipAddress;
	@NotBlank
	private String orderShipAddressDetail;
	private String orderShipMessage;

	@NotNull
	private int productId;
  @NotNull
  private int orderCount;

	public boolean getOrderUserWhether() {
		return this.orderUserWhether;
	}

}
