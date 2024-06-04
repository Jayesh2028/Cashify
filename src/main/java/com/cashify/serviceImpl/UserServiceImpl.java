package com.cashify.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cashify.exception.DataAlreadyExistsException;
import com.cashify.exception.UserNotFoundException;
import com.cashify.model.User;
import com.cashify.repo.UserRepository;
import com.cashify.request.UserAddReq;
import com.cashify.request.UserUpdateReq;
import com.cashify.response.ResponseBean;
import com.cashify.service.UserService;
import com.cashify.utils.CommonUtils;
import com.cashify.utils.UtilsConstants;

import jakarta.validation.Valid;

@Component
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseBean registerUser(@Valid UserAddReq user) {
		logger.info("Inside UserServiceImpl :: saveUser()");
		ResponseBean responseStructure = new ResponseBean();
		HttpStatus httpStatus = HttpStatus.CREATED;
		long timeStamp = System.currentTimeMillis();

		String userName = user.getUserName();
		String email = user.getEmail();
		String password = user.getPassword();
		String mobileNo = user.getMobileNo();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		try {

			if (userName != null) {
				validateUserNameAvailability(userName);

				User newUser = new User();

				if (userName != null && !userName.equals(""))
					newUser.setUserName(userName);
				else
					newUser.setUserName(null);

				if (email != null && !email.equals(""))
					newUser.setEmail(email);
				else
					newUser.setEmail(null);

				if (password != null && !password.equals(""))
					newUser.setPassword(password);
				else
					newUser.setPassword(null);

				if (mobileNo != null && !mobileNo.equals(""))
					newUser.setMobileNo(mobileNo);
				else
					newUser.setMobileNo(null);

				if (firstName != null && !firstName.equals(""))
					newUser.setFirstName(firstName);
				else
					newUser.setFirstName(firstName);

				if (lastName != null && !lastName.equals(""))
					newUser.setLastName(lastName);
				else
					newUser.setLastName(null);

				Date date = new Date();
				Timestamp timestamp2 = new Timestamp(date.getTime());

				newUser.setCreatedDate(timestamp2);
				newUser.setModifiedDate(timestamp2);
				userRepository.save(newUser);

				responseStructure.setMessage(httpStatus.getReasonPhrase());
				responseStructure.setTimeStamp(CommonUtils.currentTimeMillisToLocalTime(timeStamp));
				responseStructure.setCode(httpStatus.value());
				responseStructure.setStatus("success");
				responseStructure.setServiceName("addUser_Service");

			} else {
				responseStructure.setMessage(UtilsConstants.FAILED);
			}

		} catch (DataAlreadyExistsException e) {
			logger.error("User already exists: {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("Error While adding User: {}", e.getMessage());
			throw e;
		}

		return responseStructure;
	}

	private void validateUserNameAvailability(String userName) {
		if (isUserNameAlreadyExist(userName)) {
			throw new DataAlreadyExistsException(UtilsConstants.ALREADY_EXISTS);
		}

	}

	private boolean isUserNameAlreadyExist(String userName) {
		boolean flag = false;
		try {
			User user = userRepository.findByUserName(userName);
			if (user != null && user.getUserId() != null) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while processing isUserNameAlreadyExist {}: {}", e.getMessage());
		}

		return flag;
	}

	@Override
	public ResponseBean updateUser(Long id, UserUpdateReq userUpdateReq) {
		logger.info("Inside UserServiceImpl :: updateUser()");

		ResponseBean responseStructure = new ResponseBean();
		long timeStamp = System.currentTimeMillis();
		responseStructure.setTimeStamp(CommonUtils.currentTimeMillisToLocalTime(timeStamp));
		responseStructure.setServiceName("updateUser_Service");
		try {

			User user = userRepository.findById(id)
					.orElseThrow(() -> new UserNotFoundException(UtilsConstants.NO_RECORDS_FOUND));

			String firstName = "";
			if (userUpdateReq.getFirstName() != null && userUpdateReq.getFirstName() != "")
				firstName = userUpdateReq.getFirstName();

			String lastName = "";
			if (userUpdateReq.getLastName() != null && userUpdateReq.getLastName() != "")
				lastName = userUpdateReq.getLastName();

			String mobileNo = "";
			if (userUpdateReq.getMobileNo() != null && userUpdateReq.getMobileNo() != "")
				mobileNo = userUpdateReq.getMobileNo();

			String password = "";
			if (userUpdateReq.getPassword() != null && userUpdateReq.getPassword() != "")
				password = userUpdateReq.getPassword();

			if (user != null) {
				if (isUpdateNeeded(user, userUpdateReq)) {

					if (firstName != null && !firstName.equals(""))
						user.setFirstName(firstName);
					else
						user.setFirstName(null);

					if (lastName != null && !lastName.equals(""))
						user.setLastName(lastName);
					else
						user.setLastName(null);

					if (mobileNo != null && !mobileNo.equals(""))
						user.setMobileNo(mobileNo);
					else
						user.setMobileNo(null);

					if (password != null && !password.equals(""))
						user.setPassword(password);
					else
						user.setPassword(null);

					Date date = new Date();
					Timestamp timestamp2 = new Timestamp(date.getTime());

					user.setModifiedDate(timestamp2);
					userRepository.save(user);

					responseStructure.setCode(HttpStatus.OK.value());
					responseStructure.setStatus(UtilsConstants.SUCCESSFUL);
					responseStructure.setMessage(UtilsConstants.UPDATED);
				} else {
					responseStructure.setMessage(UtilsConstants.NO_CHANGES_NEED);
					responseStructure.setStatus(UtilsConstants.SUCCESSFUL);
					responseStructure.setCode(HttpStatus.NOT_MODIFIED.value());
				}
			}

			else {
				responseStructure.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
				responseStructure.setStatus(UtilsConstants.FAILED);
				responseStructure.setCode(HttpStatus.NOT_FOUND.value());
			}

		} catch (UserNotFoundException e) {

			logger.error("City not found: {}", e.getMessage());

			throw e;
		} catch (org.springframework.dao.DataIntegrityViolationException e) {

			logger.error("City not found: {}", e.getMessage());

			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error While updating City with id {}: {}", id, e.getMessage());

			throw e;
		}
		return responseStructure;

	}

	private boolean isUpdateNeeded(User user, UserUpdateReq userUpdateReq) {

		return !user.getFirstName().equals(userUpdateReq.getFirstName())
				|| !user.getLastName().equals(userUpdateReq.getLastName())
				|| !user.getMobileNo().equals(userUpdateReq.getMobileNo())
				|| !user.getPassword().equals(userUpdateReq.getPassword());
	}

}
