package com.campussocialmedia.campussocialmedia.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

@Repository
public class SearchRepository {

	@Autowired
	private AmazonDynamoDB client;
	
	public List<Map<String, AttributeValue>> searchByUsername(String searchText) {
		System.out.println("in Repo" + searchText);
		Map<String, AttributeValue> expressionAttributeValues =
			    new HashMap<String, AttributeValue>();
			expressionAttributeValues.put(":val", new AttributeValue(searchText));
		ScanRequest scanRequest = new ScanRequest()
	    .withTableName("UserTable")
	    .withFilterExpression("contains(userName, :val)")
	    .withProjectionExpression("userName, lastName, firstName, email, profilePhotoURL, intro")
	    .withExpressionAttributeValues(expressionAttributeValues);
		ScanResult result = client.scan(scanRequest);
		return result.getItems();
	    
	}
	public List<Map<String, AttributeValue>> searchByFirstNameOrLastName(String searchText) {
		
		Map<String, AttributeValue> expressionAttributeValues =
			    new HashMap<String, AttributeValue>();
			expressionAttributeValues.put(":val", new AttributeValue(searchText));
		ScanRequest scanRequest = new ScanRequest()
	    .withTableName("UserTable")
	    .withFilterExpression("contains(firstName, :val) or contains(lastName, :val)")
	    .withProjectionExpression("userName, lastName, firstName, email, profilePhotoURL, intro")
	    .withExpressionAttributeValues(expressionAttributeValues);
		ScanResult result = client.scan(scanRequest);
		return result.getItems();
	}
	
	public List<Map<String, AttributeValue>> searchByCollegeName(String searchText) {
		
		Map<String, AttributeValue> expressionAttributeValues =
			    new HashMap<String, AttributeValue>();
			expressionAttributeValues.put(":val", new AttributeValue(searchText));
		ScanRequest scanRequest = new ScanRequest()
	    .withTableName("UserTable")
	    .withFilterExpression("contains(collegeDetails.collegeName, :val)")
	    .withProjectionExpression("userName, lastName, firstName, email, profilePhotoURL, intro")
	    .withExpressionAttributeValues(expressionAttributeValues);
		ScanResult result = client.scan(scanRequest);
		return result.getItems();
	}
    
}
