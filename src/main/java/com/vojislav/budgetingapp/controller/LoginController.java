package com.vojislav.budgetingapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import com.vojislav.budgetingapp.domain.User;
import com.vojislav.budgetingapp.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("user",new User());
 		return "login";
	}
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("user",new User());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@ModelAttribute User user,Model model) {
		if(!StringUtils.isEmpty(user.getPassword()) && !StringUtils.isEmpty(user.getConfirmPassword())) {
			if(!user.getPassword().equals(user.getConfirmPassword())) {
				model.addAttribute("error","Password do not match");
				return "register";
			}
		}
		if(StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getConfirmPassword())) {
			model.addAttribute("error","You must choose a password");
			return "register";
		}
		userService.saveUser(user);
		
		Authentication auth=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return "redirect:/budgets";
	}
}
 