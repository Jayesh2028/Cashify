package com.cashify.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashify.request.UserAddReq;
import com.cashify.request.UserUpdateReq;
import com.cashify.response.ResponseBean;
import com.cashify.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ResponseBean> signUp(@Valid @RequestBody UserAddReq user) {
		logger.info("Inside UserController :: signUp()	");
		ResponseBean responseBean = userService.registerUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(responseBean);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseBean> updateUser(@PathVariable Long id, @RequestBody UserUpdateReq user) { // id,
		logger.info("Inside UserController :: updateUser()");
		ResponseBean responseBean = new ResponseBean();
		responseBean = userService.updateUser(id, user);
		return ResponseEntity.status(HttpStatus.OK).body(responseBean);
	}

}
