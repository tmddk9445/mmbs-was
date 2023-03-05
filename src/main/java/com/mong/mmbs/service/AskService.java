package com.mong.mmbs.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.request.ask.AskPatchRequestDto;
import com.mong.mmbs.dto.request.ask.AskPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetListResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetResponseDto;
import com.mong.mmbs.dto.response.ask.AskPatchResponseDto;
import com.mong.mmbs.dto.response.ask.AskPostResponseDto;
import com.mong.mmbs.repository.AskRepository;
import com.mong.mmbs.util.ResponseMessage;
import com.mong.mmbs.entity.AskEntity;

@Service
public class AskService {

  @Autowired AskRepository askRepository;

  // 문의 작성
  public ResponseDto<AskPostResponseDto> post(AskPostRequestDto dto){

		AskEntity askEntity = new AskEntity(dto);

		try {

			askRepository.save(askEntity);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

    AskPostResponseDto data = new AskPostResponseDto(askEntity);
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

  // 문의 리스트 출력
  public ResponseDto<AskGetListResponseDto> getList(String userId) {

		List<AskEntity> askList = new ArrayList<AskEntity>();

		try {

			askList = askRepository.findByAskWriter(userId);

		} catch(Exception exception){
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

    AskGetListResponseDto data = new AskGetListResponseDto(askList);
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}
	
  // 수정할 문의 출력
	public ResponseDto<AskGetResponseDto> get(int askId){
		
		AskEntity ask = null;

		try {

			ask = askRepository.findByAskId(askId);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

    AskGetResponseDto data = new AskGetResponseDto(ask);
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

	}

  // 문의 검색
  public ResponseDto<?> find (String userId, String askStatus, int months, String askSort) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Date.from(Instant.now().minus(months * 30, ChronoUnit.DAYS));
		String askDateTime = simpleDateFormat.format(date);

		List<AskEntity> askList = new ArrayList<AskEntity>();

		try {

			askList = askRepository.findByAskWriterAndAskDatetimeGreaterThanEqualAndAskSortContainsAndAskStatusContainsOrderByAskDatetimeDesc(userId, askDateTime, askSort, askStatus);

		} catch(Exception exception){
			exception.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess("Success", askList);
	
	}

  // 문의 수정
	public ResponseDto<AskPatchResponseDto> patch(AskPatchRequestDto dto) {

		AskEntity ask = null;
		int askId = dto.getAskId();

		try {

			ask = askRepository.findByAskId(askId);
			if (ask == null) ResponseDto.setFailed("Does Not Exist User");

      ask.patch(dto);
      askRepository.save(ask);

		} catch (Exception exception) {
			ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

    AskPatchResponseDto data = new AskPatchResponseDto(ask);
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
		
	}
	
	public ResponseDto<?> delete(String userId, int askId){

    List<AskEntity> list = new ArrayList<AskEntity>();

		try {

			AskEntity askEntity = askRepository.findByAskId(askId);
			askRepository.delete(askEntity);

      list = askRepository.findByAskWriter(userId);

		} catch (Exception exception) {
			ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, list);

	}

}
