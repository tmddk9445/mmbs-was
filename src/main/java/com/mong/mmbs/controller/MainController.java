package com.mong.mmbs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.common.constant.ResponseMessage;

@RestController
@RequestMapping(ResponseMessage.SLASH)
public class MainController {
	
	@GetMapping(ResponseMessage.NULL)
	public String Start() {
		return "Connection Successful Go For it";
	}

}
