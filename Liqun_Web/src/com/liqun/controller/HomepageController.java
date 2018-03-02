package com.liqun.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomepageController {

	@GetMapping("/")
	public String homepage() {
		return "homepage";
	}
	
}
