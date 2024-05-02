package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String userdata(@ModelAttribute User user) {
		
		//to check a user with a email present or not
		if(userService.emailExist(user)) {
			System.out.println("Duplicate user");
			
		}else {
			userService.userdata(user);
			System.out.println("user added successfully");	
		}
		return "login";
	}
	
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session) {
		if(userService.validatecredential(email,password)==true) {
			session.setAttribute("email", email);
			String role=userService.getRole(email);
			if(role.equals("admin")) {
				return "adminhome";
			}else {
				return "customerhome";
			}	
		}else {
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

	
}
