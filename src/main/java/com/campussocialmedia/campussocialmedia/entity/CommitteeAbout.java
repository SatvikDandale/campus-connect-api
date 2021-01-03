package com.campussocialmedia.campussocialmedia.entity;

import java.util.HashMap;
import java.util.List;

public class CommitteeAbout {
    private String name;
    private String userName;
    private String email;
    private String bio;
    private String logoUrl;
    private HashMap<String, String> socialLinks;
    private List<CommitteeMembers> committeeMembers;

    public CommitteeAbout(){
    }

    public CommitteeAbout(String name, String userName, String email, String bio, String logoUrl, HashMap<String,String> socialLinks, List<CommitteeMembers> committeeMembers) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.bio = bio;
        this.logoUrl = logoUrl;
        this.socialLinks = socialLinks;
        this.committeeMembers = committeeMembers;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public HashMap<String,String> getSocialLinks() {
        return this.socialLinks;
    }

    public void setSocialLinks(HashMap<String,String> socialLinks) {
        this.socialLinks = socialLinks;
    }

    public List<CommitteeMembers> getCommitteeMembers() {
        return this.committeeMembers;
    }

    public void setCommitteeMembers(List<CommitteeMembers> committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public CommitteeAbout name(String name) {
        this.name = name;
        return this;
    }

    public CommitteeAbout userName(String userName) {
        this.userName = userName;
        return this;
    }

    public CommitteeAbout email(String email) {
        this.email = email;
        return this;
    }

    public CommitteeAbout bio(String bio) {
        this.bio = bio;
        return this;
    }

    public CommitteeAbout logoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public CommitteeAbout socialLinks(HashMap<String,String> socialLinks) {
        this.socialLinks = socialLinks;
        return this;
    }

    public CommitteeAbout committeeMembers(List<CommitteeMembers> committeeMembers) {
        this.committeeMembers = committeeMembers;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", email='" + getEmail() + "'" +
            ", bio='" + getBio() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            ", socialLinks='" + getSocialLinks() + "'" +
            ", committeeMembers='" + getCommitteeMembers() + "'" +
            "}";
    }
    
}