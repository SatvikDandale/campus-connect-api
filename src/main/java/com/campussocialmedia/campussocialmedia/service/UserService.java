package com.campussocialmedia.campussocialmedia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.SignatureException;

import com.campussocialmedia.campussocialmedia.entity.PersonalDetails;
import com.campussocialmedia.campussocialmedia.entity.UserAbout;
import com.campussocialmedia.campussocialmedia.entity.UserDBEntity;
import com.campussocialmedia.campussocialmedia.entity.UserDTO;
import com.campussocialmedia.campussocialmedia.entity.UserDetailsEntity;
import com.campussocialmedia.campussocialmedia.entity.UserFollowerFollowing;
import com.campussocialmedia.campussocialmedia.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	private UserDBEntity convertToEntity(UserDTO user) {
		return modelMapper.map(user, UserDBEntity.class);
	}

	/*
	 * A complete UserAbout object is sent from frontend. If we directly use mapper
	 * to covert to DBEntity, the rest of the data will be set to null (like
	 * password, posts, followers, etc) Currently, manually setting the rest of the
	 * data (which is in DBEntity but not in UserAbout) IS THERE A BETTER WAY TO DO
	 * THIS?
	 */
	private UserDBEntity convertToEntity(UserAbout user, UserDBEntity originalObject) {
		UserDBEntity temp = modelMapper.map(user, UserDBEntity.class);
		temp.setPassword(originalObject.getPassword());
		temp.setPosts(originalObject.getPosts());
		temp.setGroups(originalObject.getGroups());
		temp.setPersonalChats(originalObject.getPersonalChats());
		temp.setFollowers(originalObject.getFollowers());
		temp.setFollowing(originalObject.getFollowing());
		return temp;
	}

	private UserDTO convertToDTO(UserDBEntity user) {
		return modelMapper.map(user, UserDTO.class);
	}

	private UserDetailsEntity convertToDetailsEntity(UserDBEntity user) {
		return modelMapper.map(user, UserDetailsEntity.class);
	}

	private UserAbout convertToAbout(UserDBEntity user) {
		return modelMapper.map(user, UserAbout.class);
	}

	private UserFollowerFollowing convertToFollowerFollowing(UserDBEntity user) {
		return modelMapper.map(user, UserFollowerFollowing.class);
	}

	public UserDTO addUser(UserDTO user) {
		List<Long> emptyList = new ArrayList<Long>();
		user.setPersonalChats(emptyList);
		user.setGroups(emptyList);
		PersonalDetails details = new PersonalDetails("-", new ArrayList<String>(), new ArrayList<String>());
		user.setPersonalDetails(details);
		user.setFollowers(new ArrayList<String>());
		user.setFollowing(new ArrayList<String>());
		user.setBio("-");
		user.setIntro("-");
		user.setPosts(new ArrayList<>());

		System.out.println(user);

		UserDBEntity userEntity = repository.addUser(convertToEntity(user));
		return convertToDTO(userEntity);
	}

	public void addFollowerFollowing(String follower, String following, String jwtUserName) {

		System.out.println(follower + ", " + following);

		if (!jwtUserName.equals(follower))
			throw new SignatureException("Token does not match with userName");
		UserDBEntity followerEntity = repository.findUserByUserName(follower);
		if (followerEntity == null)
			throw new UsernameNotFoundException("User " + follower + " Not Found");
		UserDBEntity followingEntity = repository.findUserByUserName(following);
		if (followingEntity == null)
			throw new UsernameNotFoundException("User " + following + " Not Found");

		List<String> followersList = followingEntity.getFollowers();
		// If user already in following list, No need to add the user in the list
		if (followersList.contains(follower))
			return;
		followersList.add(follower);
		followingEntity.setFollowers(followersList);
		repository.updateUser(followingEntity);
		// if not present then only add else throw exception
		List<String> followingList = followerEntity.getFollowing();
		followingList.add(following);
		followerEntity.setFollowing(followingList);
		repository.updateUser(followerEntity);
		// Nothing to return

	}

	// public UserDTO getUserByIdAndUserName(String userId, String userName) {
	// UserDBEntity userDBEntity =
	// repository.findUserByIdAndUserName(Long.parseLong(userId), userName);
	// UserDTO userDTO = convertToDTO(userDBEntity);
	// return userDTO;
	// }

	public UserDTO getUserByUserName(String userName) {
		UserDBEntity userDBEntity = repository.findUserByUserName(userName);
		UserDTO userDTO = convertToDTO(userDBEntity);
		return userDTO;
	}

	public UserDetailsEntity getUserBasicDetailsByUserName(String userName) {
		UserDBEntity userDBEntity = repository.findUserByUserName(userName);
		UserDetailsEntity userDetailsEntity = convertToDetailsEntity(userDBEntity);
		return userDetailsEntity;
	}

	public UserAbout getUserAboutByUserName(String userName) {
		UserDBEntity userDBEntity = repository.findUserByUserName(userName);
		UserAbout userAbout = convertToAbout(userDBEntity);
		return userAbout;
	}

	// The frontend doesn't need any thing apart from confirmation.
	public void updateUserAboutDetails(UserAbout user, String userName) {
		if (!userName.equals(user.getUserName())) {
			throw new SignatureException("Unauthorized User");
		}
		UserDBEntity originalUser = repository.findUserByUserName(user.getUserName());
		UserDBEntity updatedUser = convertToEntity(user, originalUser);
		System.out.println(updatedUser);
		updatedUser = repository.updateUserAboutDetails(updatedUser);
	}

	public UserFollowerFollowing getUserFollowerFollowingByUserName(String userName) {
		UserDBEntity userDBEntity = repository.findUserByUserName(userName);
		UserFollowerFollowing userAbout = convertToFollowerFollowing(userDBEntity);
		return userAbout;
	}

	public HashMap<String, List<Long>> getAllConvoName(String userName) {
		UserDTO user = getUserByUserName(userName);
		HashMap<String, List<Long>> convoNames = new HashMap<String, List<Long>>();
		convoNames.put("Personal", user.getPersonalChats());
		convoNames.put("Group", user.getGroups());
		return convoNames;
	}

	public void addProfilePhotoURL(String userName, String url) {
		UserDBEntity user = repository.findUserByUserName(userName);
		user.setProfilePhotoURL(url);
		repository.updateUser(user);
	}

}
