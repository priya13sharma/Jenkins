package com.nagarro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nagarro.entities.Librarian;
import com.nagarro.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Method to fetch User by username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Librarian librarian = userRepository.getUserByUserName(username);
		if (librarian == null) {
			throw new UsernameNotFoundException("Could not found user");
		}

		CustomUserDetails customUserDetails = new CustomUserDetails(librarian);

		return customUserDetails;
	}

}

