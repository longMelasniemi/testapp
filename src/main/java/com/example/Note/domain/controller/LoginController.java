package com.example.Note.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	/**
	 * set Default page Login.html
	 */
	@RequestMapping(value = "/")
	public String login() {
		return "/login";
	/**
	* redirect to login.html after logout
	*/		
	}
	@RequestMapping(value = "/login")
	public String loginout() {
		return "/login";
	}
}
