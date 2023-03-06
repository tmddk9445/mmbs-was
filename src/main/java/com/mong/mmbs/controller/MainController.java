package com.mong.mmbs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
	
	@GetMapping("")
	public String Start() {
		return "Connection Successful Go For it";
	}

}
