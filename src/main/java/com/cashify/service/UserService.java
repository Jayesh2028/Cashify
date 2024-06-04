package com.cashify.service;

import com.cashify.request.UserAddReq;
import com.cashify.request.UserUpdateReq;
import com.cashify.response.ResponseBean;

import jakarta.validation.Valid;

public interface UserService {

	ResponseBean registerUser(@Valid UserAddReq user);

	ResponseBean updateUser(Long id, UserUpdateReq user);

}
