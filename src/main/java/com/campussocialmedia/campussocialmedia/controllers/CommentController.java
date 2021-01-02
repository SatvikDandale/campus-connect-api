package com.campussocialmedia.campussocialmedia.controllers;

import com.campussocialmedia.campussocialmedia.entity.Comment;
import com.campussocialmedia.campussocialmedia.service.CommentService;
import com.campussocialmedia.campussocialmedia.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestHeader(name = "Authorization") String token, @RequestBody Comment comment)
    {
        String jwt = token.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        Comment newComment = service.addComment(userName, comment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @GetMapping("/comments/{postID}")
    public ResponseEntity<?> getCommentsForPostID(@PathVariable String postID)
    {
        return new ResponseEntity<>(service.getCommentsForPosts(postID), HttpStatus.OK);
    }
}
