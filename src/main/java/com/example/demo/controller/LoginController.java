package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String goToLoginPage() {
		
		return "login";
		
	}

	
	
	@GetMapping("/dash")
	public String dashboard(ModelMap model, Authentication authentication,HttpSession session) {
		return "dashboard";
	}
		   
	      
	   
}
