package com.mong.mmbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.request.gift.GiftPatchReqeustDto;
import com.mong.mmbs.dto.request.gift.GiftPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.gift.GiftGetResponseDto;
import com.mong.mmbs.dto.response.gift.GiftPatchResponseDto;
import com.mong.mmbs.dto.response.gift.GiftPostResponseDto;
import com.mong.mmbs.entity.GiftEntity;
import com.mong.mmbs.entity.OrderEntity;
import com.mong.mmbs.repository.GiftRepository;
import com.mong.mmbs.repository.OrderRepository;

import java.util.List;

@Service
public class GiftService {

	@Autowired GiftRepository giftRepository;
	@Autowired OrderRepository orderRepository;

	public ResponseDto<GiftPostResponseDto> post(GiftPostRequestDto dto){
		return null;
	}

	public ResponseDto<GiftGetResponseDto> get(int giftCode){

		GiftGetResponseDto data = null;

		List<GiftEntity> giftList = null;

		try {

			giftList = giftRepository.findAll();

			data = new GiftGetResponseDto(giftList);

		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}

		return ResponseDto.setSuccess("성공", data);

	}
	
	public ResponseDto<GiftPatchResponseDto> giftorder(GiftPatchReqeustDto dto){

		GiftPatchResponseDto data = null;

		int GiftCode = dto.getOrderGiftCode();
		String orderNumber = dto.getOrderNumber();

		OrderEntity orderEntity = null;

		try {

			orderEntity = orderRepository.findByOrderNumber(orderNumber);
			if (orderEntity == null) return ResponseDto.setFailed("Does not Exist Order");

			orderEntity.setOrderGiftCode(GiftCode);
			orderRepository.save(orderEntity);

			data = new GiftPatchResponseDto();

		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}

		return ResponseDto.setSuccess("성공", orderEntity);
		
	}
}
