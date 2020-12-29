package com.campussocialmedia.campussocialmedia.controllers;

import io.jsonwebtoken.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.Response;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.campussocialmedia.campussocialmedia.entity.UserAbout;
import com.campussocialmedia.campussocialmedia.entity.UserDTO;
import com.campussocialmedia.campussocialmedia.entity.UserFollowerFollowing;
import com.campussocialmedia.campussocialmedia.exception.ExceptionResponse;
import com.campussocialmedia.campussocialmedia.service.MediaService;
import com.campussocialmedia.campussocialmedia.service.UserService;
import com.campussocialmedia.campussocialmedia.util.JwtUtil;

@RestController
@CrossOrigin
public class UserResourceController {
	@Autowired
	private UserService service;

	@Autowired
	private MediaService amazonClient;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/test")
	public String testApi() {
		return "This is a test response for testing API";
	}

	// to return self user object
	@GetMapping("/self")
	public ResponseEntity<?> self(@RequestHeader("Authorization") String token) {
		String userName = jwtUtil.extractUsername(token.substring(7));
		// UserDTO user = service.getUserByUserName(userName);
		UserAbout user = service.getUserAboutByUserName(userName);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// no need of this endpoint now, /signup can be used
	/*
	 * @PostMapping("/user") public UserDTO saveUser(@RequestBody UserDTO user) {
	 * return service.addUser(user); }
	 */

	// returns the object of specified username
	// no need of this endpoint yet
	// need not return entire userDTO object for security reasons
	/*
	 * @GetMapping("/user/{userName}") public ResponseEntity<?>
	 * findUser(@PathVariable String userName) { try { UserDTO user =
	 * service.getUserByUserName(userName);
	 * 
	 * return new ResponseEntity<>(user, HttpStatus.OK); } catch (Exception e) { //
	 * return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	 * ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
	 * "User with username: " + userName + " not found", "Some Details");
	 * 
	 * return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND); } }
	 */

	// for searching another user
	@GetMapping("/userAbout/{userName}")
	public ResponseEntity<?> findUserAbout(@PathVariable String userName) {
		try {
			// userabout contains only the required user details in about section
			UserAbout user = service.getUserAboutByUserName(userName);

			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			// return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
					"User with username: " + userName + " not found", "Some Details");

			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
	}

	// Updated Details of user from "about" section are taken from frontend and
	// updated in database
	// Only the status is returned since it is all the frontEnd needs.
	@PostMapping("/editUser")
	public ResponseEntity<?> editUserAboutDetails(@RequestBody UserAbout userAboutObject,
			@RequestHeader(name = "Authorization") String token) throws SignatureException {
		String jwt = token.substring(7);
		String userName = jwtUtil.extractUsername(jwt);

		service.updateUserAboutDetails(userAboutObject, userName);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// returns a list of both followers and following
	@GetMapping("/userFollowerFollowing/{userName}")
	public ResponseEntity<?> findUserFollowerFollowing(@PathVariable String userName) {
		try {
			UserFollowerFollowing user = service.getUserFollowerFollowingByUserName(userName);

			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			// return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
					"User with username: " + userName + " not found", "Some Details");

			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/userConvos/{userName}")
	public ResponseEntity<?> getAllConvoNames(@PathVariable String userName) {
		try {
			HashMap<String, List<Long>> ConvosName = service.getAllConvoName(userName);
			return new ResponseEntity<>(ConvosName, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Conversations not found for the user", HttpStatus.NOT_FOUND);
		}

	}

	// just pass a username(of the person to whom you want to follow) in url as path
	// variable
	// @GetMapping("/follow/{userName}") => @PostMapping("/follow/{user2}")
	// exact username of the user who requested to follow using jwt token
	// add user2 in following list of user1 and add user1 in follower list of user2
	@PostMapping("/follow")
	public ResponseEntity<?> addFollowerFollowing(@RequestHeader(name = "Authorization") String token,
			@RequestBody Map<String, String> jsonObject) throws SignatureException {
		String jwt = token.substring(7);
		String userName = jwtUtil.extractUsername(jwt);

		service.addFollowerFollowing(jsonObject.get("follower"), jsonObject.get("following"), userName);
		return new ResponseEntity<>("Follower Added", HttpStatus.OK);

	}

	@PostMapping("/unfollow")
	public ResponseEntity<?> unfollowUser(@RequestHeader(name = "Authorization") String token,
			@RequestBody Map<String, String> jsonObject) throws SignatureException {
		String jwt = token.substring(7);
		String userName = jwtUtil.extractUsername(jwt);

		service.removeFollowerFollowing(jsonObject.get("follower"), jsonObject.get("following"), userName);
		return new ResponseEntity<>("UnFollowed", HttpStatus.OK);
	}

	// @DeleteMapping("/deleteUser/{userId}/{userName}")
	// public String deletePerson(@PathVariable String userId, @PathVariable String
	// userName) {
	// service.deleteUser(userId, userName);
	// return "User Deleted";
	// }

	@PostMapping("/uploadProfilePhoto")
	public ResponseEntity<?> uploadProfilePhoto(@RequestPart(value = "file") MultipartFile file,
			@RequestPart(value = "userName") String userName) {
		String fileURL = amazonClient.uploadFile(file);
		service.addProfilePhotoURL(userName, fileURL);
		return new ResponseEntity<>(fileURL, HttpStatus.OK);
	}

	@GetMapping("/getProfilePhoto/{userName}")
	public String getProfilePhotoForUserName(@PathVariable String userName) {
		return service.getProfilePhotoForUserName(userName);
	}
	
}
