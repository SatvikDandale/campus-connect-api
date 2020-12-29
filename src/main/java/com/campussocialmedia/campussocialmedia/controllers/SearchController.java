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
	
	@GetMapping("/searchByUsername/{searchText}")
	public List<Map<String, AttributeValue>> searchByUserName(@PathVariable String searchText) {
		return service.searchByUsername(searchText);
	}
	
	@GetMapping("/searchByName/{searchText}")
	public List<Map<String, AttributeValue>> searchByFirstNameOrLastName(@PathVariable String searchText) {
		
		return service.searchByFirstNameOrLastName(searchText);
	}
	
	@GetMapping("/searchByCollegeName/{searchText}")
	public List<Map<String, AttributeValue>> searchByCollegeName(@PathVariable String searchText) {
		
		return service.searchByCollegeName(searchText);
	}

}
