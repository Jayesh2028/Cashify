package com.cashify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cashify.response.ResponseBean;
import com.cashify.security.AuthRequest;
import com.cashify.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping("/token")
	public ResponseEntity<ResponseBean> generateToken(@RequestBody AuthRequest authRequest) {
		ResponseBean responseBean = loginService.generateToken(authRequest);
		return ResponseEntity.status(HttpStatus.OK).body(responseBean);
	}

}
