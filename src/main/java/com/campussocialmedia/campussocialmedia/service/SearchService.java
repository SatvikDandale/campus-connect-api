package com.campussocialmedia.campussocialmedia.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.campussocialmedia.campussocialmedia.repository.SearchRepository;

@Service
public class SearchService {

	@Autowired
	private SearchRepository repository;
	
	public List<Map<String, AttributeValue>> searchUserByUsername(String searchText) {
			return repository.searchUserByUsername(searchText);
	}

	public List<Map<String, AttributeValue>> searchUserByFirstNameOrLastName(String searchText) {
		
		return repository.searchUserByFirstNameOrLastName(searchText);
	}
	public List<Map<String, AttributeValue>> searchUserByCollegeName(String searchText) {
		
		return repository.searchUserByCollegeName(searchText);
	}
	
	public List<Map<String, AttributeValue>> searchCommitteeByUsername(String searchText) {
		return repository.searchComitteeByUsername(searchText);
	}
	
	public List<Map<String, AttributeValue>> searchCommitteeByName(String searchText) {
		
		return repository.searchComitteeByName(searchText);
	}
	
	public List<Map<String, AttributeValue>> searchCommitteeByCollegeName(String searchText) {
			
			return repository.searchComitteeByCollegeName(searchText);
	}
	
	
	
}
