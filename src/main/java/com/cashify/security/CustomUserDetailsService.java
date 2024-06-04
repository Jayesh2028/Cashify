package com.cashify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cashify.model.User;
import com.cashify.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = null;
		try {
			user = userRepository.findByUserName(username);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String name = user.getUserName();
		String password = user.getPassword();
		User dynamicUserList = userRepository.getDynamicUserList(name, password);

		if (dynamicUserList == null) {
			throw new UsernameNotFoundException("USER NOT FOUND");
		}

		return new CustomUser(dynamicUserList);
	}
}
