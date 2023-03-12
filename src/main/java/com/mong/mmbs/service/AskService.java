package com.mong.mmbs.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.common.constant.ResponseMessage;
import com.mong.mmbs.dto.request.ask.AskPatchRequestDto;
import com.mong.mmbs.dto.request.ask.AskPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetListResponseDto;
import com.mong.mmbs.dto.response.ask.AskDeleteResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetAskIdResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetFindResponseDto;
import com.mong.mmbs.dto.response.ask.AskPatchResponseDto;
import com.mong.mmbs.dto.response.ask.AskPostResponseDto;
import com.mong.mmbs.repository.AskRepository;
import com.mong.mmbs.entity.AskEntity;

@Service
public class AskService {

  @Autowired AskRepository askRepository;

  public ResponseDto<AskPostResponseDto> post(AskPostRequestDto dto, String userId){

    AskPostResponseDto data = null;

		try {

      AskEntity askEntity = new AskEntity(dto, userId);
			askRepository.save(askEntity);

      data = new AskPostResponseDto(askEntity);

		} catch (Exception exception) {
      exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

	  return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

  public ResponseDto<AskGetListResponseDto> getList(String userId) {

	  AskGetListResponseDto data = null;

		try {

			List<AskEntity> askList = askRepository.findByAskWriter(userId);

      data = new AskGetListResponseDto(askList);

		} catch(Exception exception){
      exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

	public ResponseDto<AskGetFindResponseDto> find(String userId, int askStatus, int months, int askSort) {
    
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = Date.from(Instant.now().minus(months * 30, ChronoUnit.DAYS));
    String askDateTime = simpleDateFormat.format(date);

		AskGetFindResponseDto data = null;
    
		try {

      List<AskEntity> askList
        = askRepository.findByAskWriterAndAskDatetimeGreaterThanEqualAndAskSortAndAskStatusOrderByAskDatetimeDesc(userId ,askDateTime, askStatus, askSort);

      data = new AskGetFindResponseDto(askList);

    } catch(Exception exception){
      exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }

    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
		
  }

	public ResponseDto<AskGetAskIdResponseDto> get(int askId){
		
    AskGetAskIdResponseDto data = null;

		try {

			AskEntity askEntity = askRepository.findByAskId(askId);

      data = new AskGetAskIdResponseDto(askEntity);

		} catch (Exception exception) {
      exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

	public ResponseDto<AskPatchResponseDto> patch(AskPatchRequestDto dto) {

    AskPatchResponseDto data = null;

		int askId = dto.getAskId();

		try {

			AskEntity askEntity = askRepository.findByAskId(askId);
			if (askEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

      askEntity.patch(dto);
      askRepository.save(askEntity);

      data = new AskPatchResponseDto(askEntity);

		} catch (Exception exception) {
      exception.printStackTrace();
			ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
		
	}
	
  // 문의 삭제
	public ResponseDto<AskDeleteResponseDto> delete(String userId, int askId){

    AskDeleteResponseDto data = null;

		try {

			AskEntity askEntity = askRepository.findByAskId(askId);
			askRepository.delete(askEntity);

      List<AskEntity> askList = askRepository.findByAskWriter(userId);

      data = new AskDeleteResponseDto(askList);

		} catch (Exception exception) {
      exception.printStackTrace();
			ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

}
