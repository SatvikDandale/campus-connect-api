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
	
	public List<Map<String, AttributeValue>> searchByUsername(String searchText) {
			return repository.searchByUsername(searchText);
	}

	public List<Map<String, AttributeValue>> searchByFirstNameOrLastName(String searchText) {
		
		return repository.searchByFirstNameOrLastName(searchText);
	}
	public List<Map<String, AttributeValue>> searchByCollegeName(String searchText) {
		
		return repository.searchByCollegeName(searchText);
	}
}
