package com.campussocialmedia.campussocialmedia.entity;

import org.springframework.web.multipart.MultipartFile;

//defines input format for incoming committee signup requests
public class CommitteeAuthenticationRequest {
    private String name;
    private String userName;
    private String email;
    private String password;
	private MultipartFile image;
	private String collegeName;
    private boolean isCollegeProfile;
    private String domainName;

    public CommitteeAuthenticationRequest() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
    
    public String getCollegeName() {
		return collegeName;
	}


	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	
	public boolean isCollegeProfile() {
		return isCollegeProfile;
	}


	public void setCollegeProfile(boolean isCollegeProfile) {
		this.isCollegeProfile = isCollegeProfile;
	}


	public CommitteeAuthenticationRequest(String name, String userName, String email, String password,
			MultipartFile image, String collegeName, boolean isCollegeProfile) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.image = image;
		this.collegeName = collegeName;
		this.isCollegeProfile = isCollegeProfile;
	}

    public CommitteeAuthenticationRequest(String name, String userName, String email, String password,
            MultipartFile image, String collegeName, boolean isCollegeProfile, String domainName) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.image = image;
        this.collegeName = collegeName;
        this.isCollegeProfile = isCollegeProfile;
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public String toString() {
        return "CommitteeAuthenticationRequest [collegeName=" + collegeName + ", domainName=" + domainName + ", email="
                + email + ", image=" + image + ", isCollegeProfile=" + isCollegeProfile + ", name=" + name
                + ", password=" + password + ", userName=" + userName + "]";
    }



}
