package com.campussocialmedia.campussocialmedia.controllers;

import java.util.Date;
import java.util.List;

import com.campussocialmedia.campussocialmedia.entity.CommitteeAbout;
import com.campussocialmedia.campussocialmedia.entity.CommitteeMembers;
import com.campussocialmedia.campussocialmedia.exception.ExceptionResponse;
import com.campussocialmedia.campussocialmedia.service.CommitteeService;
import com.campussocialmedia.campussocialmedia.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.SignatureException;


//committee endpoints required:
//get self committee object - done
//get committee about - done
//edit committee about - done
//get a list of followers - done
//add a follower - done
//remove a follower - done
//get list of committee members - done
//add a committee member - done
//delete a committee member - done
//endpoints related to committee posts - todo


//login endpoints - todo
//signup endpoints - todo

@RestController
@CrossOrigin
public class CommitteeController {
    @Autowired
    private CommitteeService committeeService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/committee/self")
    public ResponseEntity<?> self(@RequestHeader("Authorization") String token) {
        String userName = jwtUtil.extractUsername(token.substring(7));
        // UserDTO user = service.getUserByUserName(userName);
        CommitteeAbout committeeAbout = committeeService.getCommitteeAboutByUserName(userName);
        return new ResponseEntity<>(committeeAbout, HttpStatus.OK);
    }

    //tested
    @GetMapping("/committee/about")
    public ResponseEntity<?> getCommitteeAbout(@RequestHeader(name = "Authorization") String token) throws SignatureException {

        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);

        try{
           CommitteeAbout committeeAbout = committeeService.getCommitteeAboutByUserName(userName);
           return new ResponseEntity<>(committeeAbout, HttpStatus.OK);
        }
        catch(Exception e){
            // return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                    "Committee with committeename: " + userName + " not found", "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    //frontend just need confirmation about updated details
    @PostMapping("/committee/editCommittee")
    public ResponseEntity<?> editUserAboutDetails(@RequestBody CommitteeAbout committeeAboutObject,
        @RequestHeader(name = "Authorization") String token) throws SignatureException {
        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);

        committeeService.updateCommitteeAboutDetails(committeeAboutObject, userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //return List<String> of followers of committee
    @GetMapping("/committee/getFollowers")
    public ResponseEntity<?> getCommitteeFollowers(@RequestHeader(name = "Authorization") String token) throws SignatureException {

        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);

        try{
            List<String> committeeFollowers = committeeService.getCommitteeFollowers(userName);
            return new ResponseEntity<>(committeeFollowers, HttpStatus.OK);

        } catch (Exception e) {
            // return new ResponseEntity<>("Committee not found", HttpStatus.NOT_FOUND);
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                    "Committee with username: " + userName + " not found", "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/committee/addFollower")
    public ResponseEntity<?> addFollower(@RequestBody String followerUserName,
            @RequestHeader(name = "Authorization") String token) throws SignatureException {

            String jwt = token.substring(7);
            String userName = jwtUtil.extractUsername(jwt);

            try{
                committeeService.addFollower(userName, followerUserName);

                return new ResponseEntity<>(HttpStatus.OK);
            } catch(Exception e){
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                        "Committee with username: " + userName + " not found", "Some Details");

                return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping("/committee/removeFollower")
    public ResponseEntity<?> removeFollower(@RequestBody String followerUserName,
            @RequestHeader(name = "Authorization") String token) throws SignatureException {

            String jwt = token.substring(7);
            String userName = jwtUtil.extractUsername(jwt);

            try{
                if(committeeService.removeFollower(userName, followerUserName)){
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

            } catch(Exception e){
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                        "Error removing follower", "Some Details");

                return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
            }

    }

    //return a List<CommitteeMember> object
    @GetMapping("/committee/members")
    public ResponseEntity<?> getCommitteeMembers(@RequestHeader(name = "Authorization") String token) throws SignatureException {
        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);

        try{
            List<CommitteeMembers> committeeMembers = committeeService.getCommitteeMembers(userName);
            return new ResponseEntity<>(committeeMembers, HttpStatus.OK);
        } catch(Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                    "Error getting list of committee members", "Some Details");

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
    }

    //optimse the way this is done
    //first check if the added username is valid, else pass error to frontend  
    @PostMapping("/commitee/addMember")
    public ResponseEntity<?> addCommitteeMember(@RequestBody CommitteeMembers committeeMemberObject,
        @RequestHeader(name = "Authorization") String token) throws SignatureException {
            String jwt = token.substring(7);
            String userName = jwtUtil.extractUsername(jwt);    

            try{
                committeeService.addCommitteeMember(committeeMemberObject, userName);
                return new ResponseEntity<>(HttpStatus.OK);
            }catch(Exception e){
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                        "Error in adding committee member", "some details");

                return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

    @PostMapping("/committee/deleteMember")
    public ResponseEntity<?> deleteMember(@RequestBody CommitteeMembers committeeMemberObject,
        @RequestHeader(name = "Authorization") String token) throws SignatureException {
            String jwt = token.substring(7);
            String userName = jwtUtil.extractUsername(jwt);

            try{
                if(committeeService.deleteCommitteeMember(committeeMemberObject, userName)){
                return new ResponseEntity<>(HttpStatus.OK);
                }else{
                        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                            "Error in deleting committee member", "Member does not exist in this committee");

                        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
                }
            } catch(Exception e){
                ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Error in removing committee member", 
                        "Some Details");

                return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
            }
            
    }
}
