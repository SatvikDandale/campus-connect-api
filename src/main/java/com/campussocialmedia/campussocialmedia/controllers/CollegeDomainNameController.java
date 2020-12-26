package com.campussocialmedia.campussocialmedia.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.campussocialmedia.campussocialmedia.entity.CollegeDomainNames;
import com.campussocialmedia.campussocialmedia.exception.ExceptionResponse;
import com.campussocialmedia.campussocialmedia.service.CollegeDomainNameService;

@RestController
public class CollegeDomainNameController {

	@Autowired
	private CollegeDomainNameService service;

	// If a college registers on the platform then add its domain name in database
	// if domain name is vit.edu, then "@vit.edu" will be added to database
	@PostMapping("/addDomainName")
	public ResponseEntity<?> saveDomainName(@RequestBody CollegeDomainNames domainName) {
		System.out.println("in /addDomainName");
		try {
			CollegeDomainNames domainNameObj = service.addDomainName(domainName);

			return new ResponseEntity<>(domainNameObj, HttpStatus.OK);
		} catch (Exception e) {
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
					"Something Went Wrong " + domainName + " not found", "Some Details");

			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
	}

	// verifies if a college exists with the domain name
	// @GetMapping("/college/{domainName}")
	// public ResponseEntity<?> findCollege(@PathVariable String domainName) {
	// 	// there is a problem here. Exception handling isn't working
	// 	try {
	// 		CollegeDomainNames domainNameObj = service.getCollegeByDomainName(domainName);
	// 		System.out.println(domainNameObj);
	// 		return new ResponseEntity<>(domainNameObj, HttpStatus.OK);
	// 	} catch (Exception e) {
	// 		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
	// 				"College with DomainName: " + domainName + " not found", "Some Details");

	// 		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	// 	}
	// }

	/*
	 * it takes emailId and extracts domain name from it. then verifies if any
	 * college exists in the database with the domain name
	 */
	// Tested by satvik. Works fine.
	@GetMapping("/college/{emailId}")
	public ResponseEntity<?> findCollegeByEmailId(@PathVariable String emailId) {
		// System.out.println(emailId);

		try {
			String[] arrOfStr = emailId.split("@");
			String domainName = "@" + arrOfStr[arrOfStr.length - 1];
			// System.out.println(domainName);
			CollegeDomainNames domainNameObj = service.getCollegeByDomainName(domainName);
			return new ResponseEntity<>(domainNameObj.getCollegeName(), HttpStatus.OK);

		} catch (Exception e) {
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
					"College with DomainName for this email id " + emailId + " not found", "Some Details");
			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
	}

}
