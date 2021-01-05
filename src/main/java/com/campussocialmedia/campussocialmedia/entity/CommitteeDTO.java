package com.campussocialmedia.campussocialmedia.entity;

import java.util.HashMap;
import java.util.List;

public class CommitteeDTO {
    private String name;
    private String userName;
    private String email;
    private String password;
    private String bio;
    private String logoUrl;
    private List<CommitteeMembers> committeeMembers;
    private List<String> followers;
    private HashMap<String, String> socialLinks;
    private List<Long> posts;
    private boolean isEnabled;
    private boolean isCollegeProfile;

    public CommitteeDTO() {
    }

    public CommitteeDTO(String name, String userName, String email, String password, String bio, String logoUrl,
			List<CommitteeMembers> committeeMembers, List<String> followers, HashMap<String, String> socialLinks,
			List<Long> posts, boolean isEnabled, boolean isCollegeProfile) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.logoUrl = logoUrl;
		this.committeeMembers = committeeMembers;
		this.followers = followers;
		this.socialLinks = socialLinks;
		this.posts = posts;
		this.isEnabled = isEnabled;
		this.isCollegeProfile = isCollegeProfile;
	}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<CommitteeMembers> getCommitteeMembers() {
        return committeeMembers;
    }

    public void setCommitteeMembers(List<CommitteeMembers> committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public HashMap<String, String> getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(HashMap<String, String> socialLinks) {
        this.socialLinks = socialLinks;
    }

    public List<Long> getPosts() {
        return posts;
    }

    public void setPosts(List<Long> posts) {
        this.posts = posts;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isCollegeProfile() {
        return isCollegeProfile;
    }

    public void setCollegeProfile(boolean isCollegeProfile) {
        this.isCollegeProfile = isCollegeProfile;
    }

    @Override
    public String toString() {
        return "CommitteeDTO [bio=" + bio + ", committeeMembers=" + committeeMembers + ", email=" + email
                + ", followers=" + followers + ", isCollegeProfile=" + isCollegeProfile + ", isEnabled=" + isEnabled
                + ", logoUrl=" + logoUrl + ", name=" + name + ", posts=" + posts + ", socialLinks=" + socialLinks
                + ", userName=" + userName + "]";
    }

    
}
