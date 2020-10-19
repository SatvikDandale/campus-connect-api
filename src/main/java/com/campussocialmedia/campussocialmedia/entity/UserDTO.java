package com.campussocialmedia.campussocialmedia.entity;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
//	private String userId;
	private String userName;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private List<Long> personalChats;
	private List<Long> groups;
	private List<Long> followers;
	private List<Long> following;
	private List<Experience> experiences;
	private List<Long> posts;
	private List<Fishpond> fishponds;
	private College collegeDetails;
	private PersonalDetails personalDetails;
	private String bio;
	private String intro;
	private HashMap<String, String> socialLinks;
	
	public UserDTO() {
	}
	public UserDTO(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public UserDTO(String userName, String email, String password, String firstName, String lastName,
			College collegeDetails) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.collegeDetails = collegeDetails;
	}
	public UserDTO(String userName, String email, String password, String firstName, String lastName, String phone,
			List<Long> personalChats, List<Long> groups, List<Long> followers, List<Long> following,
			List<Experience> experiences, List<Long> posts, List<Fishpond> fishponds, College collegeDetails,
			PersonalDetails personalDetails, String bio, String intro, HashMap<String, String> socialLinks) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.personalChats = personalChats;
		this.groups = groups;
		this.followers = followers;
		this.following = following;
		this.experiences = experiences;
		this.posts = posts;
		this.fishponds = fishponds;
		this.collegeDetails = collegeDetails;
		this.personalDetails = personalDetails;
		this.bio = bio;
		this.intro = intro;
		this.socialLinks = socialLinks;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Long> getPersonalChats() {
		return personalChats;
	}
	
	public void setPersonalChats(List<Long> personalChats) {
		this.personalChats = personalChats;
	}
	public List<Long> getGroups() {
		return groups;
	}
	public void setGroups(List<Long> groups) {
		this.groups = groups;
	}
	
	public List<Long> getFollowers() {
		return followers;
	}
	public void setFollowers(List<Long> followers) {
		this.followers = followers;
	}
	
	public List<Long> getFollowing() {
		return following;
	}
	public void setFollowing(List<Long> following) {
		this.following = following;
	}
	
	public List<Experience> getExperiences() {
		return experiences;
	}
	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}
	
	public List<Long> getPosts() {
		return posts;
	}
	public void setPosts(List<Long> posts) {
		this.posts = posts;
	}
	
	public List<Fishpond> getFishponds() {
		return fishponds;
	}
	public void setFishponds(List<Fishpond> fishponds) {
		this.fishponds = fishponds;
	}
	
	public College getCollegeDetails() {
		return collegeDetails;
	}
	public void setCollegeDetails(College collegeDetails) {
		this.collegeDetails = collegeDetails;
	}
	
	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}
	
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public HashMap<String, String> getSocialLinks() {
		return socialLinks;
	}
	public void setSocialLinks(HashMap<String, String> socialLinks) {
		this.socialLinks = socialLinks;
	}
	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phone=" + phone + ", personalChats=" + personalChats + ", groups=" + groups
				+ ", followers=" + followers + ", following=" + following + ", experiences=" + experiences + ", posts="
				+ posts + ", fishponds=" + fishponds + ", collegeDetails=" + collegeDetails + ", personalDetails="
				+ personalDetails + ", bio=" + bio + ", intro=" + intro + ", socialLinks=" + socialLinks + "]";
	}
	

}
