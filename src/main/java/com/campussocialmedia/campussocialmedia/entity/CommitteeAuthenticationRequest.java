package com.campussocialmedia.campussocialmedia.entity;

import org.springframework.web.multipart.MultipartFile;

//defines input format for incoming committee signup requests
public class CommitteeAuthenticationRequest {
    private String name;
    private String userName;
    private String email;
    private String password;
	private MultipartFile image;

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

    public CommitteeAuthenticationRequest(String name, String userName, String email, String password,
            MultipartFile image) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    @Override
    public String toString() {
        return "CommitteeAuthenticationRequest [email=" + email + ", image=" + image + ", name=" + name + ", password="
                + password + ", userName=" + userName + "]";
    }


    
}
