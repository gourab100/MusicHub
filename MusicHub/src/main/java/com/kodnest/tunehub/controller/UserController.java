package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.model.LoginData;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.service.UserService;

import jakarta.servlet.http.HttpSession;

//@CrossOrigin("*")
//@RestController
@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	SongService songService;
	
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
	

//	@PostMapping("/validate")
//	public String validate(@RequestBody LoginData loginData, HttpSession session, Model model) {
//		System.out.println(loginData+">>>>>");
//			  if(userService.validatecredential(loginData.getEmail(), loginData.getPassword())==true) {
//				
//			   session.setAttribute("email", loginData.getEmail());
//			   String role = userService.getRole(loginData.getEmail());
//			   if(role.equals("admin")) {
//
//			    return"adminhome";
//
//			   }else{
//			     User user = userService.getUser(loginData.getEmail());
//			     boolean userstatus = user.isPremium();
//			     List<Song> fetchAllSongs = songService.fetchAllSongs();
//			     model.addAttribute("songs", fetchAllSongs);
//			     model.addAttribute("ispremium", userstatus);
//
//			     return "customerhome";
//			   }
//			  }
//
//			  else {
//			   return "login";
//
//			  }
//			 }
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session, Model model) {

		if(userService.validatecredential(email, password) == true){
			
			String role = userService.getRole(email);
			
			session.setAttribute("email", email);
			
			if(role.equals("admin")) {
				return "adminhome";
			}
			else {
				
				User user = userService.getUser(email);
				boolean userstatus = user.isPremium();
				List<Song> fetchAllSongs = songService.fetchAllSongs();
				model.addAttribute("songs", fetchAllSongs);
				
				model.addAttribute("ispremium", userstatus);
				return "customerhome";
			}
		}	
		else {
			return "login";
		}	
	}
	
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

	
}










//
//public String validate(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session,Model model) {
//	if(userService.validatecredential(email,password)==true) {
//		session.setAttribute("email", email);
//		String role=userService.getRole(email);
//		if(role.equals("admin")) {
//			return "adminhome";
//		}else {
//			User user=userService.getUser(email);
//			boolean userstatus=user.isPremium();
//			List<Song> fetchAllSongs= songService.fetchAllSongs();
//			model.addAttribute("songs", fetchAllSongs);
//			model.addAttribute("isPremium", userstatus);
//			return "customerhome";
//			
//		}	
//	}else {
//		return "login";
//	}
//}
