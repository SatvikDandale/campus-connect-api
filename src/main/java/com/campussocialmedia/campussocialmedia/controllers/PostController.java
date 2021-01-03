package com.campussocialmedia.campussocialmedia.controllers;

import java.util.List;
import java.util.Map;

import com.campussocialmedia.campussocialmedia.entity.Post;
import com.campussocialmedia.campussocialmedia.entity.PostCreationRequest;
import com.campussocialmedia.campussocialmedia.service.PostService;
import com.campussocialmedia.campussocialmedia.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private PostService service;

    @Autowired
    private JwtUtil jwtUtil;

    /*
     * This endpoint is for creating new posts. The user will send a form-data
     * (PostCreationRequest).
     */
    @PostMapping("/post")
    public ResponseEntity<?> addPost(@RequestHeader(name = "Authorization") String token,
            @ModelAttribute PostCreationRequest post) {

        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        post.setUserName(userName);
        Post newPost = service.addPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }

    /*
     * This endpoint is to get the Post Entity using the unique "postID"
     */
    @GetMapping("/post/{postID}")
    public ResponseEntity<Post> findPostByID(@PathVariable String postID) {
        Post post = service.findPostByID(postID);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /*
     * This endpoint is to get all the posts created by a user using the userName
     */
    @GetMapping("/posts/{userName}")
    public ResponseEntity<?> findPostsByUserName(@PathVariable String userName) {
        List<Post> posts = service.findPostsByUserName(userName);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/allPosts")
    public ResponseEntity<?> findPostsForAllUserNames(@RequestBody Map<String, List<String>> jsonObject, @RequestHeader(name = "Authorization") String token){

        String jwt = token.substring(7);
        String requester = jwtUtil.extractUsername(jwt);
        List<String> userNames = jsonObject.get("userNames");
        return new ResponseEntity<>(service.findAllPostsByUserNames(userNames, requester), HttpStatus.OK);
    }


    // Add the userName of the users who liked the post
    @PostMapping("/addLike")
    public ResponseEntity<?> addLikeToPost(@RequestHeader(name = "Authorization") String token, @RequestBody Map<String, String> jsonObject) {
        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        String postID = jsonObject.get("postID");
        service.addLikeToPost(userName, postID);
        return new ResponseEntity<>("Liked", HttpStatus.OK);
    }

    @PostMapping("/removeLike")
    public ResponseEntity<?> removeLikeToPost(@RequestHeader(name = "Authorization") String token, @RequestBody Map<String, String> jsonObject) {
        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        String postID = jsonObject.get("postID");
        service.removeLike(userName, postID);
        return new ResponseEntity<>("Like Removed", HttpStatus.OK);
    }

    
}
