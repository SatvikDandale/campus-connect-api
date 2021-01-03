package com.campussocialmedia.campussocialmedia.service;

import java.util.List;

import com.campussocialmedia.campussocialmedia.entity.Committee;
import com.campussocialmedia.campussocialmedia.entity.CommitteeAbout;
import com.campussocialmedia.campussocialmedia.entity.CommitteeMembers;
import com.campussocialmedia.campussocialmedia.repository.CommitteeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.SignatureException;

@Service
public class CommitteeService {
    @Autowired
    private CommitteeRepository committeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private CommitteeAbout convertToAbout(Committee committee) {
        return modelMapper.map(committee, CommitteeAbout.class);
    }

    private Committee convertToEntity(CommitteeAbout committeeAbout, Committee originalObject) {
        Committee temp = modelMapper.map(committeeAbout, Committee.class);
        temp.setPassword(originalObject.getPassword());
        temp.setPosts(originalObject.getPosts());
        // temp.setGroups(originalObject.getGroups());
        // temp.setPersonalChats(originalObject.getPersonalChats());
        temp.setFollowers(originalObject.getFollowers());
        // temp.setFollowing(originalObject.getFollowing());
        return temp;
    }

    public CommitteeAbout getCommitteeAboutByUserName(String userName){
        Committee committee = committeeRepository.findCommitteeByUserName(userName);
        CommitteeAbout committeeAbout = convertToAbout(committee);
        return committeeAbout;
    }

    public void updateCommitteeAboutDetails(CommitteeAbout committeeAboutObject,String userName){
        if (!userName.equals(committeeAboutObject.getUserName())) {
            throw new SignatureException("Unauthorized User");
        }

        Committee originalCommitteeObject  = committeeRepository.findCommitteeByUserName(userName);
        Committee updatedCommitteeObject = convertToEntity(committeeAboutObject, originalCommitteeObject);
        updatedCommitteeObject = committeeRepository.updateCommitteeAboutDetails(updatedCommitteeObject);
    }

    public List<String> getCommiteeFollowers(String userName){
        return  committeeRepository.getCommitteeFollowers(userName);
    }

    public void addFollower(String committeeUserName, String followerUserName){
        Committee committee = committeeRepository.findCommitteeByUserName(committeeUserName);
        List<String> currentFollowers = committee.getFollowers();
        currentFollowers.add(followerUserName);

        committeeRepository.updateCommitteeDatabase(committee);   
    }

    public boolean removeFollower(String committeeUserName, String followerUserName){
        Committee committee = committeeRepository.findCommitteeByUserName(committeeUserName);
        List<String> currentFollowers = committee.getFollowers();

        if(currentFollowers.remove(followerUserName)){
            committeeRepository.updateCommitteeDatabase(committee);
            return true;
        }else{
            return false;
        }
    }

    public List<CommitteeMembers> getCommitteeMembers(String userName){
        return committeeRepository.getCommitteeMembersRepo(userName);
    }

    public void addCommitteeMember(CommitteeMembers committeeMemberObject, String userName){
        Committee committee = committeeRepository.findCommitteeByUserName(userName);
        List<CommitteeMembers> currentMembers =  committee.getCommitteeMembers();

        currentMembers.add(committeeMemberObject);
        committee.setCommitteeMembers(currentMembers);

        committeeRepository.updateCommitteeDatabase(committee);
    }

    public boolean deleteCommitteeMember(CommitteeMembers committeeMemberObject, String userName){
        Committee committee = committeeRepository.findCommitteeByUserName(userName);
        List<CommitteeMembers> currentMembers = committee.getCommitteeMembers();

        if(currentMembers.remove(committeeMemberObject)){
            committee.setCommitteeMembers(currentMembers);
            committeeRepository.updateCommitteeDatabase(committee);

            return true;
        }
        else{
            return false;
        }
    }
}

