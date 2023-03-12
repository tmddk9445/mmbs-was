package com.mong.mmbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.repository.GiftRepository;
import com.mong.mmbs.repository.OrderDetailRepository;
import com.mong.mmbs.repository.OrderRepository;
import com.mong.mmbs.repository.ProductRepository;

import com.mong.mmbs.dto.request.order.OrderPostRequestDto;
import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.request.order.GiftPatchReqeustDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.order.GiftGetResponseDto;
import com.mong.mmbs.dto.response.order.GiftPatchResponseDto;
import com.mong.mmbs.dto.response.order.OrderGetListResponseDto;
import com.mong.mmbs.dto.response.order.OrderPostResponseDto;

import com.mong.mmbs.entity.OrderEntity;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.entity.GiftEntity;
import com.mong.mmbs.entity.OrderDetailEntity;

@Service
public class OrderService {

  @Autowired OrderRepository orderRepository;
  @Autowired OrderDetailRepository orderDetailRepository;
  @Autowired ProductRepository productRepository;
  @Autowired GiftRepository giftRepository;

  public ResponseDto<OrderPostResponseDto> postOrder(OrderPostRequestDto dto) {
    
    OrderPostResponseDto data = null;

    int productId = dto.getProductId();
    
    try {

      ProductEntity productEntity = productRepository.findByProductSeq(productId);
      if (productEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER_PRODUCT);

			OrderEntity orderEntity = new OrderEntity(dto, productEntity);
			OrderDetailEntity orderDetailEntity = new OrderDetailEntity(dto, orderEntity, productEntity);

      orderRepository.save(orderEntity);
      orderDetailRepository.save(orderDetailEntity);

      data = new OrderPostResponseDto(orderEntity, orderDetailEntity);

    } catch (Exception exception) {
			exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

    }

    String guestPassword = dto.getOrderGuestPassword();
    String guestPasswordCheck = dto.getOrderGuestPasswordCheck();

    String orderUserId = dto.getOrderUserId();

    if (orderUserId != null) return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    else {

      if (guestPassword != null) {
        try {
  
          if (!guestPassword.equals(guestPasswordCheck))
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_GUESTPASSWORD);
  
        } catch (Exception exception) {
					exception.printStackTrace();
          return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

      }
		}
    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

  }

  public ResponseDto<List<OrderGetListResponseDto>> getOrderList(String userId) {

		List<OrderGetListResponseDto> data = new ArrayList<OrderGetListResponseDto>();

		try {

			List<OrderEntity> orderList = orderRepository.findByOrderUserId(userId);

			for ( OrderEntity order : orderList ) {

				List<OrderDetailEntity> detailList = orderDetailRepository.findByOrderNumber(order.getOrderNumber());
				OrderGetListResponseDto resultItem = new OrderGetListResponseDto(order, detailList);
				
				data.add(resultItem);

			}

		} catch(Exception exception){
			exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    
	}

	public ResponseDto<GiftGetResponseDto> getGiftCode(int giftCode){

		GiftGetResponseDto data = null;

		try {

			List<GiftEntity> giftList = giftRepository.findAll();

			data = new GiftGetResponseDto(giftList);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}
	
	public ResponseDto<GiftPatchResponseDto> patchGift(GiftPatchReqeustDto dto){

		GiftPatchResponseDto data = null;

		int orderGiftCode = dto.getOrderGiftCode();
		String orderNumber = dto.getOrderNumber();

		try {

			OrderEntity orderEntity = orderRepository.findByOrderNumber(orderNumber);
			if (orderEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_ORDER);

			orderEntity.setOrderGiftCode(orderGiftCode);
			orderRepository.save(orderEntity);

			data = new GiftPatchResponseDto(dto);

		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
		
	}
}