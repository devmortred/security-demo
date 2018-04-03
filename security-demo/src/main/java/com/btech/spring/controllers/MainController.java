package com.btech.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping(value="/")
	public String home() {
		return "Public Section";
	}
	
	@GetMapping(value="/private")
	public String privateArea() {
		return "Private Section - Access token is OK";
	}
}
