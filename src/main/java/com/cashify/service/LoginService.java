package com.cashify.service;

import com.cashify.response.ResponseBean;
import com.cashify.security.AuthRequest;

public interface LoginService {

	ResponseBean generateToken(AuthRequest authRequest);

}
