package com.campussocialmedia.campussocialmedia.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.campussocialmedia.campussocialmedia.service.SearchService;

@RestController
@CrossOrigin
public class SearchController {
	
	@Autowired
    private SearchService service;
	
	@GetMapping("/searchByUserName/{searchText}")
	public List<Map<String, AttributeValue>> searchUserByUserName(@PathVariable String searchText) {
		return service.searchUserByUsername(searchText);
	}
	
	@GetMapping("/searchByName/{searchText}")
	public List<Map<String, AttributeValue>> searchUserByFirstNameOrLastName(@PathVariable String searchText) {
		
		return service.searchUserByFirstNameOrLastName(searchText);
	}
	
	@GetMapping("/searchByCollegeName/{searchText}")
	public List<Map<String, AttributeValue>> searchUserByCollegeName(@PathVariable String searchText) {
		
		return service.searchUserByCollegeName(searchText);
	}
	
	@GetMapping("/searchByUserNameCommittee/{searchText}")
	public List<Map<String, AttributeValue>> searchCommitteeByUserName(@PathVariable String searchText) {
		return service.searchCommitteeByUsername(searchText);
	}
	
	@GetMapping("/searchByNameCommittee/{searchText}")
	public List<Map<String, AttributeValue>> searchCommitteeByUsername(@PathVariable String searchText) {
		
		return service.searchCommitteeByName(searchText);
	}
	
	@GetMapping("/searchByCollegeNameCommittee/{searchText}")
	public List<Map<String, AttributeValue>> searchCommitteeByCollegeName(@PathVariable String searchText) {
		
		return service.searchCommitteeByCollegeName(searchText);
	}
	

}
