package com.campussocialmedia.campussocialmedia.entity;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

@DynamoDBTable(tableName = "UserTable")
public class UserDBEntity {

	private String userName;
	private String email;
	private boolean isEnabled;  //to check wheteher email verification is done or not
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private List<Long> personalChats;
	private List<Long> groups;
	private List<String> followers;
	private List<String> following;
	private List<Experience> experiences;
	private List<Long> posts;
	private College collegeDetails;
	private PersonalDetails personalDetails;
	private String bio;
	private String intro;
	private HashMap<String, String> socialLinks;
	private String profilePhotoURL;

	public UserDBEntity() {
		super();
	}

	public UserDBEntity(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public UserDBEntity(String userName, String email, String password, String firstName, String lastName, String phone,
			List<Long> personalChats, List<Long> groups, List<String> followers, List<String> following,
			List<Experience> experiences, List<Long> posts, College collegeDetails, PersonalDetails personalDetails,
			String bio, String intro, HashMap<String, String> socialLinks, String profilePhotoURL) {
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
		this.collegeDetails = collegeDetails;
		this.personalDetails = personalDetails;
		this.bio = bio;
		this.intro = intro;
		this.socialLinks = socialLinks;
		this.profilePhotoURL = profilePhotoURL;
	}

	@DynamoDBHashKey(attributeName = "userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@DynamoDBAttribute
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@DynamoDBAttribute
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@DynamoDBAttribute
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@DynamoDBAttribute
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBAttribute
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@DynamoDBAttribute
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@DynamoDBAttribute
	public List<Long> getPersonalChats() {
		return personalChats;
	}

	public void setPersonalChats(List<Long> personalChats) {
		this.personalChats = personalChats;
	}

	@DynamoDBAttribute
	public List<Long> getGroups() {
		return groups;
	}

	public void setGroups(List<Long> groups) {
		this.groups = groups;
	}

	@DynamoDBAttribute
	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	@DynamoDBAttribute
	public List<String> getFollowing() {
		return following;
	}

	public void setFollowing(List<String> following) {
		this.following = following;
	}

	@DynamoDBAttribute
	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	@DynamoDBAttribute
	public List<Long> getPosts() {
		return posts;
	}

	public void setPosts(List<Long> posts) {
		this.posts = posts;
	}

	@DynamoDBAttribute
	public College getCollegeDetails() {
		return collegeDetails;
	}

	public void setCollegeDetails(College collegeDetails) {
		this.collegeDetails = collegeDetails;
	}

	@DynamoDBAttribute
	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	@DynamoDBAttribute
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@DynamoDBAttribute
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@DynamoDBAttribute
	public HashMap<String, String> getSocialLinks() {
		return socialLinks;
	}

	public void setSocialLinks(HashMap<String, String> socialLinks) {
		this.socialLinks = socialLinks;
	}

	@DynamoDBAttribute
	public String getProfilePhotoURL() {
		return profilePhotoURL;
	}

	public void setProfilePhotoURL(String profilePhotoURL) {
		this.profilePhotoURL = profilePhotoURL;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((collegeDetails == null) ? 0 : collegeDetails.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((experiences == null) ? 0 : experiences.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((followers == null) ? 0 : followers.hashCode());
		result = prime * result + ((following == null) ? 0 : following.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((intro == null) ? 0 : intro.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((personalChats == null) ? 0 : personalChats.hashCode());
		result = prime * result + ((personalDetails == null) ? 0 : personalDetails.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
		result = prime * result + ((profilePhotoURL == null) ? 0 : profilePhotoURL.hashCode());
		result = prime * result + ((socialLinks == null) ? 0 : socialLinks.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDBEntity other = (UserDBEntity) obj;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (collegeDetails == null) {
			if (other.collegeDetails != null)
				return false;
		} else if (!collegeDetails.equals(other.collegeDetails))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (experiences == null) {
			if (other.experiences != null)
				return false;
		} else if (!experiences.equals(other.experiences))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (followers == null) {
			if (other.followers != null)
				return false;
		} else if (!followers.equals(other.followers))
			return false;
		if (following == null) {
			if (other.following != null)
				return false;
		} else if (!following.equals(other.following))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (intro == null) {
			if (other.intro != null)
				return false;
		} else if (!intro.equals(other.intro))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (personalChats == null) {
			if (other.personalChats != null)
				return false;
		} else if (!personalChats.equals(other.personalChats))
			return false;
		if (personalDetails == null) {
			if (other.personalDetails != null)
				return false;
		} else if (!personalDetails.equals(other.personalDetails))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (posts == null) {
			if (other.posts != null)
				return false;
		} else if (!posts.equals(other.posts))
			return false;
		if (profilePhotoURL == null) {
			if (other.profilePhotoURL != null)
				return false;
		} else if (!profilePhotoURL.equals(other.profilePhotoURL))
			return false;
		if (socialLinks == null) {
			if (other.socialLinks != null)
				return false;
		} else if (!socialLinks.equals(other.socialLinks))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDBEntity [bio=" + bio + ", collegeDetails=" + collegeDetails + ", email=" + email + ", experiences="
				+ experiences + ", firstName=" + firstName + ", followers=" + followers + ", following=" + following
				+ ", groups=" + groups + ", intro=" + intro + ", isEnabled=" + isEnabled + ", lastName=" + lastName
				+ ", password=" + password + ", personalChats=" + personalChats + ", personalDetails=" + personalDetails
				+ ", phone=" + phone + ", posts=" + posts + ", profilePhotoURL=" + profilePhotoURL + ", socialLinks="
				+ socialLinks + ", userName=" + userName + "]";
	}
	

	/*
	 * public UserDBEntity() { super(); }
	 * 
	 * public UserDBEntity(String userName, String password) { super();
	 * this.userName = userName; this.password = password; } public
	 * UserDBEntity(String userId, String userName, String email, String password,
	 * String firstName, String lastName, String phone, List<String> personalChats,
	 * List<String> groups, List<String> followers, List<String> following, College
	 * collegeDetails) { this.userId = userId; this.userName = userName; this.email
	 * = email; this.password = password; this.firstName = firstName; this.lastName
	 * = lastName; this.phone = phone; this.personalChats = personalChats;
	 * this.groups = groups; this.followers = followers; this.following = following;
	 * this.collegeDetails = collegeDetails; }
	 * 
	 * 
	 * @DynamoDBAttribute public String getUserId() { return userId; }
	 * 
	 * public void setUserId(String userId) { this.userId = userId; }
	 * 
	 * @DynamoDBHashKey(attributeName = "userName") public String getUserName() {
	 * return userName; }
	 * 
	 * public void setUserName(String userName) { this.userName = userName; }
	 * 
	 * @DynamoDBAttribute public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * @DynamoDBAttribute public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * @DynamoDBAttribute public String getFirstName() { return firstName; }
	 * 
	 * public void setFirstName(String firstName) { this.firstName = firstName; }
	 * 
	 * @DynamoDBAttribute public String getLastName() { return lastName; }
	 * 
	 * public void setLastName(String lastName) { this.lastName = lastName; }
	 * 
	 * @DynamoDBAttribute public String getPhone() { return phone; }
	 * 
	 * public void setPhone(String phone) { this.phone = phone; }
	 * 
	 * @DynamoDBAttribute public List<Long> getPersonalChats() { return
	 * personalChats; }
	 * 
	 * public void setPersonalChats(List<Long> personalChats) { this.personalChats =
	 * personalChats; }
	 * 
	 * @DynamoDBAttribute public List<Long> getGroups() { return groups; }
	 * 
	 * public void setGroups(List<Long> groups) { this.groups = groups; }
	 * 
	 * @DynamoDBAttribute public List<String> getFollowers() { return followers; }
	 * 
	 * public void setFollowers(List<String> followers) { this.followers =
	 * followers; }
	 * 
	 * @DynamoDBAttribute public List<String> getFollowing() { return following; }
	 * 
	 * public void setFollowing(List<String> following) { this.following =
	 * following; }
	 * 
	 * public UserDBEntity() { super(); }
	 * 
	 * public UserDBEntity(String userName, String password) { super();
	 * this.userName = userName; this.password = password; }
	 * 
	 * @DynamoDBAttribute public College getCollegeDetails() { return
	 * collegeDetails; }
	 * 
	 * public void setCollegeDetails(College collegeDetails) { this.collegeDetails =
	 * collegeDetails; }
	 * 
	 * @Override public String toString() { return "UserDBEntity [collegeDetails=" +
	 * collegeDetails + ", email=" + email + ", firstName=" + firstName +
	 * ", followers=" + followers + ", following=" + following + ", groups=" +
	 * groups + ", lastName=" + lastName + ", password=" + password +
	 * ", personalChats=" + personalChats + ", phone=" + phone + ", userId=" +
	 * userId + ", userName=" + userName + "]"; }
	 * 
	 */

}
