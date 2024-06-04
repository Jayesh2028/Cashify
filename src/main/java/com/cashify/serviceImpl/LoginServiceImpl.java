package com.cashify.serviceImpl;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.cashify.exception.InvalidCredentialsException;
import com.cashify.model.User;
import com.cashify.repo.UserRepository;
import com.cashify.response.LoginResponseBean;
import com.cashify.response.ResponseBean;
import com.cashify.security.AuthRequest;
import com.cashify.security.JwtUtil;
import com.cashify.service.LoginService;
import com.cashify.utils.CommonUtils;
import com.cashify.utils.UtilsConstants;

@Component
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LogManager.getLogger(LoginServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public ResponseBean generateToken(AuthRequest authRequest) {
		String serviceName = "generateToken_Service";
		LoginResponseBean loginResponseBean = new LoginResponseBean();

		try {
			String userName = authRequest.getUserName();
			String password = authRequest.getPassword();
			System.out.println("password>> " + password);

			String publicKey = UtilsConstants.PUBLIC_KEY;
			String secretKey = authRequest.getKey();

			User user = validateUserCredentials(userName, password);
			Long userId = user.getUserId();

			String credUserName = user.getUserName();
			String generateToken = jwtUtil.generateToken(credUserName);
			System.out.println("generateToken >>>" + generateToken);
			loginResponseBean.setToken(generateToken);

			return Optional.ofNullable(generateToken).map(token -> {
				String message = UtilsConstants.MESSAGE;

				List<Object> objList = Collections.singletonList(loginResponseBean);
				return new CommonUtils().responseStructure(objList, Instant.now().toEpochMilli(), message, serviceName,
						null, null, null);
			}).orElseGet(() -> new CommonUtils().responseForFailed(Instant.now().toEpochMilli(), serviceName));

		} catch (/* DecryptionException | */InvalidCredentialsException | AuthenticationException ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			logger.error("Internal Server Error", ex);
			throw new RuntimeException("Internal Server Error", ex);
		}
	}

	User validateUserCredentials(String userName, String password) {
		Optional<User> userOptional = userRepository.findByUserNametoken(userName).stream().findFirst();

		User user = userOptional.orElseThrow(() -> new InvalidCredentialsException("Invalid username/password"));

		String storedPassword = user.getPassword();
		if (!password.equalsIgnoreCase(storedPassword)) {
			throw new InvalidCredentialsException("Invalid username/password");
		}

		return user;
	}

}
