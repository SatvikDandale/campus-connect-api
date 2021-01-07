package com.campussocialmedia.campussocialmedia.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campussocialmedia.campussocialmedia.entity.CommitteeDTO;
import com.campussocialmedia.campussocialmedia.entity.UserDTO;

/*
class UserDetailsService is used by spring security to get the details of user
We have to create our own version of UserDetailsService to fetch the user details from our database
The job of MyUserDetailsService is to return a User object containing username and password of the 
user whose username is passed or throw UsernameNotFoundException. 
*/

@Service("User")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService service;

	@Autowired
	private CommitteeService committeeService;

	private CommitteeDTO committee;
	
	private UserDTO user;
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public UserDetails loadUserByUsername(String inputUserName) throws UsernameNotFoundException {

		String userName;
		String password;

		try {
			// System.out.println("1");
			user = service.getUserByUserName(inputUserName);
			userName = user.getUserName();
			password = user.getPassword();
		} catch (Exception e) {
			// System.out.println("2");
			try {
				// System.out.println("3");
				committee = committeeService.getCommitteeByUserName(inputUserName);
				userName = committee.getUserName();
				password = committee.getPassword();

				return new User(userName, password, new ArrayList<>());
				

			} catch (Exception ex) {
				//System.out.println("4");
				// throw new UsernameNotFoundException("Committee not found", e);
				throw new UsernameNotFoundException("Profile not found", ex);
			}
		}

		//Keeping the user authorities blank
		return new User(userName, password, new ArrayList<>());
	}
}
