package com.campussocialmedia.campussocialmedia.service;

import java.util.ArrayList;
import java.util.List;

import com.campussocialmedia.campussocialmedia.entity.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommitteeService committeeService;

    public List<Post> getFeedForUserName(String userName) {

        List<String> userNames = userService.getFollowing(userName);

        return postService.findAllPostsByUserNames(userNames, userName);

    }
    public List<Post> getFeedForCommittee(String userName) {

        // List<String> userNames = committeeService.getCommitteeFollowers(userName);
        
        // Committee's news feed is basically their own posts
        return postService.findPostsByUserName(userName);

    }

}
