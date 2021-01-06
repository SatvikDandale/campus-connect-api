package com.campussocialmedia.campussocialmedia.service;

import java.util.ArrayList;

import com.campussocialmedia.campussocialmedia.entity.CommitteeDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("Committee")
public class MyCommitteeDetailsService implements UserDetailsService {
    @Autowired
    private CommitteeService committeeService;

    private CommitteeDTO committee;

    public CommitteeDTO getUser() {
        return committee;
    }

    public void setUser(CommitteeDTO committee) {
        this.committee = committee;
    }

    @Override
    public UserDetails loadUserByUsername(String inputUserName) throws UsernameNotFoundException {
        System.out.println("inside *****");


        String userName;
        String password;

        try {
            committee = committeeService.getCommitteeByUserName(inputUserName);
            userName = committee.getUserName();
            password = committee.getPassword();
        } catch (UsernameNotFoundException e) {
            System.out.println("from MyCommitteeDetailsService.java -> UsernameNotFoundException");
            throw new UsernameNotFoundException("User not found", e);
        }

        // Keeping the user authorities blank
        return new User(userName, password, new ArrayList<>());
    }
}
