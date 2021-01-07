package com.campussocialmedia.campussocialmedia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.campussocialmedia.campussocialmedia.entity.Post;
import com.campussocialmedia.campussocialmedia.entity.ReportDBEntity;
import com.campussocialmedia.campussocialmedia.entity.UserAbout;
import com.campussocialmedia.campussocialmedia.entity.UserDTO;
import com.campussocialmedia.campussocialmedia.exception.ExceptionResponse;
import com.campussocialmedia.campussocialmedia.service.PostService;
import com.campussocialmedia.campussocialmedia.service.ReportService;
import com.campussocialmedia.campussocialmedia.service.UserService;

import io.jsonwebtoken.SignatureException;

@RestController
@CrossOrigin
public class ReportedPostController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
    private PostService postService;
	
	@PostMapping("/reportPost/{postId}")
	public ResponseEntity<?> reportPost(@PathVariable String postId) {
		try {
			Post post = postService.findPostByID(postId);
			UserDTO userDTO = userService.getUserByUserName(post.getUserName());
			try {
				ReportDBEntity reportDBEntity = reportService.findReportEntityByCollegeName(userDTO.getCollegeDetails().getCollegeName());
				
				if(reportDBEntity.getPostIDs().containsKey(postId)){
					Integer count = reportDBEntity.getPostIDs().get(postId);
					reportDBEntity.getPostIDs().put(postId, count+1);
				}
				else {
					reportDBEntity.getPostIDs().put(postId,1);
				}
				reportService.updateReport(reportDBEntity);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch(Exception e) {
				
				HashMap<String, Integer> postIDs = new HashMap<String, Integer>();
				postIDs.put(postId, 1);
				ReportDBEntity reportDBEntity = new ReportDBEntity(userDTO.getCollegeDetails().getCollegeName(), postIDs);
		
				reportService.addReport(reportDBEntity);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		catch(Exception ex) {
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
					"Post with postID: " + postId + " not found", "Some Details");

			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/reportPost/{collegeName}")
	public ResponseEntity<?> getReportedPost(@PathVariable String collegeName) {
		
		ReportDBEntity reportDBEntity = reportService.findReportEntityByCollegeName(collegeName);
		if(reportDBEntity!=null) {
				
			return new ResponseEntity<>(reportDBEntity, HttpStatus.OK);	
		}
		else {
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
					"No reported posts found for college with collegeName :  " + collegeName , "Some Details");

			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	@PostMapping("/reportPost/{postId}")
	public ResponseEntity<?> reportPost(@PathVariable String postId) {
		Post post = postService.findPostByID(postId);
		UserDTO userDTO = userService.getUserByUserName(post.getUserName());
		try {
			ReportDBEntity reportDBEntity = reportService.findCollegeByCollegeName(userDTO.getCollegeDetails().getCollegeName());
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			List<String> postIDs = new ArrayList<>();
			postIDs.add(postId);
			ReportDBEntity reportDBEntity = new ReportDBEntity(userDTO.getCollegeDetails().getCollegeName(), postIDs);
			return new ResponseEntity<>(reportDBEntity,HttpStatus.OK);
		}
	}
	
	@GetMapping("/reportPost/{collegeName}")
	public ResponseEntity<?> getReportedPost(@PathVariable String collegeName) {
		try {
			ReportDBEntity reportDBEntity = reportService.findCollegeByCollegeName(collegeName);
			return new ResponseEntity<>(reportDBEntity, HttpStatus.OK);	
		}
		catch(Exception e) {
			List<String> postIDs = new ArrayList<>();
			ReportDBEntity reportDBEntity = new ReportDBEntity(collegeName, postIDs);
			return new ResponseEntity<>(reportDBEntity, HttpStatus.OK);
		}
	}
	*/

}
