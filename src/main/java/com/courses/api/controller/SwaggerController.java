package com.courses.api.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Profile("dev")
public class SwaggerController {
	
	@RequestMapping("/")
	public String home () {
        return "redirect:/swagger-ui.html";
	} 
} 