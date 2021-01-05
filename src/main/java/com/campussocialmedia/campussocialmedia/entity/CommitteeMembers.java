package com.campussocialmedia.campussocialmedia.entity;

public class CommitteeMembers {
    private String userName;
    private String role;

    public CommitteeMembers() {
    }

    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CommitteeMembers [role=" + role + ", userName=" + userName + "]";
    }

    public CommitteeMembers(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    
}
