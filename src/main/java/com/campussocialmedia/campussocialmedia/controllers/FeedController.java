package com.campussocialmedia.campussocialmedia.controllers;

import com.campussocialmedia.campussocialmedia.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/feed/{userName}")
    public ResponseEntity<?> getFeedForUserName(@PathVariable String userName) {
        return new ResponseEntity<>(feedService.getFeedForUserName(userName), HttpStatus.OK);
    }
    @GetMapping("/feed/committee/{userName}")
    public ResponseEntity<?> getFeedForCommittee(@PathVariable String userName) {
        return new ResponseEntity<>(feedService.getFeedForCommittee(userName), HttpStatus.OK);
    }
    
}
