package com.yeren.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("auth")
@Controller
public class UserController {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLoginPage(HttpServletRequest request,@RequestParam(value = "error", required = false) boolean error,ModelMap model) {
		if (error == true) {
			model.put("error","You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		String parameter = request.getParameter("username");
		String parameter2 = request.getParameter("password");
//		return "crud/site/siteList";
//		return "/site/listByPage";
		return "testSuccess";
	}
}
