package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shopping.dtoResponse.ResponseDto;
import com.shopping.request.UserSignInReq;
import com.shopping.request.UserSignupReq;
import com.shopping.service.ProductService;
import com.shopping.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseDto signUp(@RequestBody UserSignupReq user) {
		return userService.signUp(user);
	}

	// signin
	@PostMapping("/signin")
	public ResponseDto signIn(@RequestBody UserSignInReq user) {
		return userService.signIn(user);
	}

	// logout
	@GetMapping("/logout")
	public ResponseDto logout() {
		return userService.logout();
	}
	
	
}
