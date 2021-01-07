package com.campussocialmedia.campussocialmedia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.mail.SendFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.core.userdetails.User;

import com.campussocialmedia.campussocialmedia.entity.AuthenticationRequest;
import com.campussocialmedia.campussocialmedia.entity.AuthenticationResponse;
import com.campussocialmedia.campussocialmedia.entity.CommitteeAbout;
import com.campussocialmedia.campussocialmedia.entity.CommitteeAuthenticationRequest;
import com.campussocialmedia.campussocialmedia.entity.CommitteeAuthenticationResponse;
import com.campussocialmedia.campussocialmedia.entity.CommitteeDTO;
import com.campussocialmedia.campussocialmedia.entity.ConfirmationToken;
import com.campussocialmedia.campussocialmedia.entity.UserAbout;
import com.campussocialmedia.campussocialmedia.entity.UserDTO;
import com.campussocialmedia.campussocialmedia.entity.UserDetailsEntity;
import com.campussocialmedia.campussocialmedia.entity.UserPasswordEntity;
import com.campussocialmedia.campussocialmedia.exception.ExceptionResponse;
import com.campussocialmedia.campussocialmedia.repository.ConfirmationTokenRepository;
import com.campussocialmedia.campussocialmedia.service.CommitteeService;
import com.campussocialmedia.campussocialmedia.service.EmailSenderService;
// import com.campussocialmedia.campussocialmedia.service.MyUserDetailsService;
import com.campussocialmedia.campussocialmedia.service.UserService;
import com.campussocialmedia.campussocialmedia.util.JwtUtil;

/*
This class defines and implements the API endpoints for "/login" & "/signUp"
*/
@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	// @Autowired
	// private MyUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommitteeService committeeService;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private Environment env;

	/*
	 * The logic of signUp endpoint is to use AuthenticationManager to check if the
	 * username in the incoming AuthenticationRequest exists and act accordingly.
	 */
	@PostMapping(value = "/signUp")
	public ResponseEntity<?> signUp(@ModelAttribute AuthenticationRequest authenticationRequest) throws Exception {

		// Try authenticating the given UserName and Password.
		// If no exception is thrown, then these credentials are already used.
		// If this UserName does not exists, then continue signUp. It will cause
		// AuthenticationException for unknown users.

		try {
			System.out.println(authenticationRequest);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (AuthenticationException e) {
			// This user does not exist so user can be created
			// System.out.println("New user created here:" + userDTO);
			try {
				// generate token and send a verification link
				ConfirmationToken confirmationToken = new ConfirmationToken(authenticationRequest.getUserName());

				confirmationTokenRepository.addConfirmationToken(confirmationToken);

				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(authenticationRequest.getEmail());
				mailMessage.setSubject("Complete Registration!");
				mailMessage.setFrom("campus.connect.official1@gmail.com");
				mailMessage.setText("To confirm your account, please click here :\n" + env.getProperty("url")
						+ "/confirm-account?token=" + confirmationToken.getConfirmationToken());
				// emailSenderService.sendEmail(mailMessage);
				System.out.println(System.getenv("serverURL"));
				emailSenderService.sendSynchronousMail(mailMessage);

				// Now as mail is sent add the user to database
				UserDTO userDTO = userService.addUser(authenticationRequest);
				return new ResponseEntity<>("Registered!! Complete Email Verification",
						org.springframework.http.HttpStatus.CREATED);
			} catch (MailSendException ex) {
				return new ResponseEntity<>("Email Address not valid. Error sending the verification link.",
						HttpStatus.NOT_FOUND);
			} catch (Exception ex) {
				System.out.println(ex);
				// if failed to send a verification link
				return new ResponseEntity<>("Error in sending the verification link",
						org.springframework.http.HttpStatus.FORBIDDEN);

			}

		}
		// If no exception is returned by the AuthenticationManager, then the user with
		// passed
		// userName already exists.
		return new ResponseEntity<>(new ExceptionResponse(new Date(), "User Already Exists", "/signUp"),
				org.springframework.http.HttpStatus.CONFLICT);
	}

	@PostMapping("/committee/signUp")
	public ResponseEntity<?> committeeSignUp(@ModelAttribute CommitteeAuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			System.out.println(authenticationRequest);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (AuthenticationException e) {
			// This user does not exist so user can be created
			// System.out.println("New user created here:" + userDTO);
			try {
				// generate token and send a verification link
				ConfirmationToken confirmationToken = new ConfirmationToken(authenticationRequest.getUserName());

				confirmationTokenRepository.addConfirmationToken(confirmationToken);

				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(authenticationRequest.getEmail());
				mailMessage.setSubject("Complete Registration!");
				mailMessage.setFrom("campus.connect.official1@gmail.com");
				mailMessage.setText("To confirm your account, please click here :\n" + env.getProperty("url")
						+ "/committee/confirm-account?token=" + confirmationToken.getConfirmationToken());
				emailSenderService.sendEmail(mailMessage);
				// System.out.println("serverURL -> " + System.getenv("serverURL"));
				// System.out.println("serverURL -> " + env.getProperty("url"));
				// emailSenderService.sendSynchronousMail(mailMessage);

				// Now as mail is sent add the user to database
				committeeService.addCommittee(authenticationRequest);
				return new ResponseEntity<>("Registered!! Complete Email Verification",
						org.springframework.http.HttpStatus.CREATED);
			} catch (MailSendException ex) {
				return new ResponseEntity<>("Email Address not valid. Error sending the verification link.",
						HttpStatus.NOT_FOUND);
			} catch (Exception ex) {
				System.out.println(ex);
				// if failed to send a verification link
				return new ResponseEntity<>("Error in sending the verification link",
						org.springframework.http.HttpStatus.FORBIDDEN);

			}

		}
		// If no exception is returned by the AuthenticationManager, then the user with
		// passed
		// userName already exists.
		return new ResponseEntity<>(new ExceptionResponse(new Date(), "Committee Already Exists", "/signUp"),
				org.springframework.http.HttpStatus.CONFLICT);
	}

	// Verification link for user
	@RequestMapping(value = "/user/confirm-account", method = { RequestMethod.GET })
	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {

		// System.out.println(confirmationToken);
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		// System.out.println(token);

		if (token != null) {
			UserDTO userDTO = userService.getUserByUserName(token.getUsername());
			userDTO.setEnabled(true); // isEnabled is set to true which means user is verified
			userService.updateUser(userDTO); // update user
			return new ResponseEntity<>("Verified", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("The link is invalid or broken", HttpStatus.FORBIDDEN);

		}

		// return modelAndView;
	}

	//Verification link for Committee and college profile
	@RequestMapping(value="/committee/confirm-account", method= {RequestMethod.GET})
	public ResponseEntity<?> confirmCommitteeAccount(@RequestParam("token")String confirmationToken)
	{
		
		System.out.println(confirmationToken);
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		System.out.println(token);
		
		if(token != null)
		{
			CommitteeDTO committeeDTO = committeeService.getCommitteeByUserName(token.getUsername());
			committeeDTO.setEnabled(true);  //isEnabled is set to true which means user is verified
			committeeService.updateCommitteeDTO(committeeDTO, token.getUsername());   //update user
		   return new ResponseEntity<>("Verified", HttpStatus.OK);  
		}
		else
		{
			
			return new ResponseEntity<>("The link is invalid or broken", HttpStatus.FORBIDDEN);
			
		}

	}
	// Login for committee and college profile
	@RequestMapping(value = "/committee/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenForCommittee(
			@RequestBody CommitteeAuthenticationRequest authenticationRequest) throws Exception {

		System.out.println(authenticationRequest);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Committee credentials could not be authenticated", HttpStatus.NOT_FOUND);
		}

		CommitteeAbout committeeAbout = committeeService
				.getCommitteeAboutByUserName(authenticationRequest.getUserName());
		if (committeeAbout.isEnabled()) { // if user is veirfied then only allow to login
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(
					authenticationRequest.getUserName(), authenticationRequest.getPassword(), new ArrayList<>());
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			return new ResponseEntity<>(new CommitteeAuthenticationResponse(jwt, committeeAbout), HttpStatus.OK);
		}
		return new ResponseEntity<>("Not verified", HttpStatus.FORBIDDEN);
	}

	/*
	 * AuthenticationManager passes the userName from incoming AuthenticationRequest
	 * to MyUserDetailsService class to get the user object from the database as per
	 * the incoming userName If no user is found with the incoming
	 * AuthenticationRequest username, then AuthenticationException is thrown and
	 * appropriate ResponseEntity is returned
	 * 
	 * MyUserDetailsService returns a User object containing the username and
	 * password from the database. The password from the User object returned from
	 * MyUserDetailsService is matched with the password in the incoming
	 * AuthenticationRequest. If the password does not match, we throw a
	 * BadCredentialsException and return the appropriate ResponseEntity for the
	 * same
	 * 
	 * If the entire authentication by AuthenticationManager is successful, then we
	 * generate the JWT token as per the user details and return the generated JWT
	 * token and user as repsonse.
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		System.out.println(authenticationRequest);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
				authenticationRequest.getPassword()));
	
		UserDetailsEntity user = userService.getUserBasicDetailsByUserName(authenticationRequest.getUserName());
		if (user.isEnabled()) { // if user is veirfied then only allow to login
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(
					authenticationRequest.getUserName(), authenticationRequest.getPassword(), new ArrayList<>());
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			return new ResponseEntity<>(new AuthenticationResponse(jwt, user), HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Not verified", HttpStatus.FORBIDDEN);
	}

	// Route to change password
	@RequestMapping(value = "/changePassword", method = { RequestMethod.POST })
	public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token,
			@RequestBody Map<String, String> jsonObject) {
		// extract username from jwt token
		String userName = jwtTokenUtil.extractUsername(token.substring(7));
		if (userName != null) {
			UserPasswordEntity userPasswordEntity = userService.getPasswordEntityUserByUserName(userName); // fetch
																											// current
																											// password
																											// from
																											// databse
			// checks if current password entered by user and Database password match
			// If matched update user password with new password
			if (jsonObject.get("current_password").equals(userPasswordEntity.getPassword())) {
				userService.updateUserPassword(userName, jsonObject.get("new_password"));
				return new ResponseEntity<>("Password Changed!!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Incorrect current_password", HttpStatus.FORBIDDEN);

			}

		}
		return new ResponseEntity<>("The user must be logged in", HttpStatus.UNAUTHORIZED);

	}
	
	@RequestMapping(value="/forgotPassword", method= {RequestMethod.POST})
	public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> jsonObject)
	{
		try {
			UserAbout userAbout = userService.getUserAboutByUserName(jsonObject.get("userName"));
			try {
				// generate token and send a password reset link
				ConfirmationToken confirmationToken = new ConfirmationToken(jsonObject.get("userName"));

				confirmationTokenRepository.addConfirmationToken(confirmationToken);

				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(userAbout.getEmail());
				mailMessage.setSubject("Click the link below to change the Password");
				mailMessage.setFrom("campus.connect.official1@gmail.com");
				mailMessage.setText("To confirm your account, please click here :\n" + env.getProperty("frontend-url")
						+ "/reset-password?token=" + confirmationToken.getConfirmationToken());
				//emailSenderService.sendEmail(mailMessage);
				// System.out.println("serverURL -> " + System.getenv("serverURL"));
				// System.out.println("serverURL -> " + env.getProperty("url"));
				emailSenderService.sendSynchronousMail(mailMessage);

				return new ResponseEntity<>("A link has been send to the registered emailId",
						org.springframework.http.HttpStatus.OK);
			} catch (MailSendException ex) {
				return new ResponseEntity<>("Email Address not valid. Error sending the verification link.",
						HttpStatus.NOT_FOUND);
			} catch (Exception ex) {
				System.out.println(ex);
				// if failed to send a verification link
				return new ResponseEntity<>("Error in sending the verification link",
						org.springframework.http.HttpStatus.FORBIDDEN);
			}

		}
		catch(Exception e) {
			return new ResponseEntity<>("The user with username "+jsonObject.get("userName")+" not found", HttpStatus.NOT_FOUND);
		}
		
	
	}
	@RequestMapping(value="/reset-password", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> resetPassword(@RequestParam("token")String confirmationToken,	@RequestBody Map<String, String> jsonObject )
    {
		
		//System.out.println(confirmationToken);
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        //System.out.println(token);
        
        if(token != null)
        {
        	UserDTO userDTO =  userService.getUserByUserName(token.getUsername());
            userDTO.setPassword(jsonObject.get("password"));  //isEnabled is set to true which means user is verified
            userService.updateUser(userDTO);   //update user
           return new ResponseEntity<>("Password changed", HttpStatus.OK);  
        }
        else
        {
        	return new ResponseEntity<>("The link is invalid or broken", HttpStatus.FORBIDDEN);
            
        }

    }
	
	
}
