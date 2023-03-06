package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.request.ask.AskPatchRequestDto;
import com.mong.mmbs.dto.request.ask.AskPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetListResponseDto;
import com.mong.mmbs.dto.response.ask.AskDeleteResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetAskIdResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetFindResponseDto;
import com.mong.mmbs.dto.response.ask.AskPatchResponseDto;
import com.mong.mmbs.dto.response.ask.AskPostResponseDto;
import com.mong.mmbs.service.AskService;
import com.mong.mmbs.util.EndPoint;

@RestController
@RequestMapping(EndPoint.ASK)
public class AskController {

  @Autowired AskService askService;

  @PostMapping(EndPoint.ASK_POST_NULL)
  public ResponseDto<AskPostResponseDto> post(@RequestBody AskPostRequestDto requestBody) {
    ResponseDto<AskPostResponseDto> result = askService.post(requestBody);
    return result;
  }
  
  @GetMapping(EndPoint.ASK_GET_LIST)
  public ResponseDto<AskGetListResponseDto> getList(@AuthenticationPrincipal String userId) { 
    ResponseDto<AskGetListResponseDto> result = askService.getList(userId);
		return result;
	}

  @GetMapping(EndPoint.ASK_GET_ASKID)
  public ResponseDto<AskGetAskIdResponseDto> get(@PathVariable("askId") int askId) {
    ResponseDto<AskGetAskIdResponseDto> result = askService.get(askId);
    return result;
  }

  @GetMapping(EndPoint.ASK_GET_FIND)
  public ResponseDto<AskGetFindResponseDto> find(@AuthenticationPrincipal String userId, @PathVariable("askStatus") String askStatus, @PathVariable("months") int months, @PathVariable("askSort") String askSort) {
      ResponseDto<AskGetFindResponseDto> result = askService.find(userId, askStatus, months, askSort);
      return result;
  }
  
  @PatchMapping(EndPoint.ASK_PATCH_NULL)
  public ResponseDto<AskPatchResponseDto> patch(@RequestBody AskPatchRequestDto requestBody) {
    ResponseDto<AskPatchResponseDto> result = askService.patch(requestBody);
    return result;
  }

  @DeleteMapping(EndPoint.ASK_DELETE_ASKID)
  public ResponseDto<AskDeleteResponseDto> delete(@AuthenticationPrincipal String userId, @PathVariable("askId") int askId){
    ResponseDto<AskDeleteResponseDto> result = askService.delete(userId, askId);
    return result;
  }

}
