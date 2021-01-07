package com.campussocialmedia.campussocialmedia.entity;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "CommitteeTable")
public class Committee{
    private String name;
    private String userName;
    private String email;
    private String collegeName;
    private String password;
    private String bio;
    private String logoUrl;
    private List<CommitteeMembers> committeeMembers;
    private List<String> followers;
    private HashMap<String, String> socialLinks;
    private List<Long> posts;
    private boolean isEnabled;
    private boolean isCollegeProfile;
    public Committee(){
    }

    public Committee(String userName, String password){
        super();
        this.userName=userName;
        this.password=password;
    }

    @DynamoDBAttribute
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    @DynamoDBHashKey(attributeName = "userName")
    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    @DynamoDBAttribute
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    @DynamoDBAttribute
    public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@DynamoDBAttribute
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    @DynamoDBAttribute
    public String getBio(){
        return bio;
    }

    public void setBio(String bio){
        this.bio=bio;
    }

    @DynamoDBAttribute
    public String getLogoUrl(){
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl){
        this.logoUrl=logoUrl;
    }

    @DynamoDBAttribute
    public List<CommitteeMembers> getCommitteeMembers(){
        return committeeMembers;
    }

    public void setCommitteeMembers(List<CommitteeMembers> committeeMembers){
        this.committeeMembers=committeeMembers;
    }

    @DynamoDBAttribute
    public List<String> getFollowers(){
        return followers;
    }

    public void setFollowers(List<String> followers){
        this.followers=followers;
    }

    @DynamoDBAttribute
    public HashMap<String,String> getSocialLinks(){
        return socialLinks;
    }

    public void setSocialLinks(HashMap<String,String> socialLinks){
        this.socialLinks=socialLinks;
    }

    @DynamoDBAttribute
    public List<Long> getPosts(){
        return posts;
    }

    public void setPosts(List<Long> posts){
        this.posts=posts;
    }

    @DynamoDBAttribute
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @DynamoDBAttribute
    public boolean isCollegeProfile() {
        return isCollegeProfile;
    }

    public void setCollegeProfile(boolean isCollegeProfile) {
        this.isCollegeProfile = isCollegeProfile;
    }

	public Committee(String name, String userName, String email, String collegeName, String password, String bio,
			String logoUrl, List<CommitteeMembers> committeeMembers, List<String> followers,
			HashMap<String, String> socialLinks, List<Long> posts, boolean isEnabled, boolean isCollegeProfile) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.collegeName = collegeName;
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

	@Override
	public String toString() {
		return "Committee [name=" + name + ", userName=" + userName + ", email=" + email + ", collegeName="
				+ collegeName + ", password=" + password + ", bio=" + bio + ", logoUrl=" + logoUrl
				+ ", committeeMembers=" + committeeMembers + ", followers=" + followers + ", socialLinks=" + socialLinks
				+ ", posts=" + posts + ", isEnabled=" + isEnabled + ", isCollegeProfile=" + isCollegeProfile + "]";
	}

    
}