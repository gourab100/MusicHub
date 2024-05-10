package com.kodnest.tunehub.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.CustomerService;
import com.kodnest.tunehub.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;

	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}

//    @GetMapping("/pay")
//    public String pay(HttpSession session, Model model) {
//        String email = (String) session.getAttribute("email");
//        if (email != null) {
//            User user = userService.getUser(email);
//            if (user != null && user.isPremium()) {
//                List<Song> songlist = customerService.fetchAllSongs();
//                model.addAttribute("songs", songlist);
//                return "viewsongs";
//            }
//        }
//        return "pay";
//    }


	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session) {

		int  amount = 1;
		Order order=null;
		try {
			RazorpayClient razorpay=new RazorpayClient("rzp_test_S0vcNUf9pDDq5V", "sR0WAFWEhjEZUF5Et5IZKYpv");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);



		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
	}


	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId,
			@RequestParam String signature) {
		try {
			// Initialize Razorpay client with your API key and secret
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_S0vcNUf9pDDq5V","sR0WAFWEhjEZUF5Et5IZKYpv");
			
			// Create a signature verification data string
			String verificationData = orderId + "|" + paymentId;

			// Use Razorpay's utility function to verify the signature
			boolean isValidSignature = Utils.verifySignature(verificationData, signature, 
					"sR0WAFWEhjEZUF5Et5IZKYpv");

			return isValidSignature;
		} catch (RazorpayException e) {
			e.printStackTrace();
			return false;
		}
	}


	@GetMapping("/payment-success")
	public String paymentSuccess(HttpSession session,Model model) {
		String mail =  (String) session.getAttribute("email");
		User user = userService.getUser(mail);
		user.setPremium(true);
		userService.updateUser(user);
		return "customerhome";
	   	
	}

//	  @GetMapping("/payment-success")
//	    public String paymentSuccess(HttpSession session, Model model) {
//	        String mail = (String) session.getAttribute("email");
//	        if (mail != null) {
//	            User user = userService.getUser(mail);
//	            if (user != null) {
//	                user.setPremium(true);
//	                userService.updateUser(user);
//	                List<Song> songlist = customerService.fetchAllSongs();
//	                model.addAttribute("songs", songlist);
//	                return "viewsongs";
//	            }
//	        }
//	        // Redirect to payment failure if user or email is null
//	        return "redirect:/payment-failure";
//	    }
	
	
	@GetMapping("/payment-failure")
	public String paymentFailure() {

		return "customerhome";
	}




}