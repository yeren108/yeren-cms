package com.yeren.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String notFound() {
		return "common/404";
	}
}
