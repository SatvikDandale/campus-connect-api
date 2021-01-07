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
	
	// For User
	public List<Map<String, AttributeValue>> searchUserByUsername(String searchText) {
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
	public List<Map<String, AttributeValue>> searchUserByFirstNameOrLastName(String searchText) {
		
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
	
	public List<Map<String, AttributeValue>> searchUserByCollegeName(String searchText) {
		
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
	
	//For Committee
	public List<Map<String, AttributeValue>> searchComitteeByUsername(String searchText) {
		
		Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		expressionAttributeValues.put(":val", new AttributeValue(searchText));
		
		Map<String, String> expressionAttributeNames = new HashMap<String, String>();
		expressionAttributeNames.put("#n", "name");
		
		ScanRequest scanRequest = new ScanRequest()
	    .withTableName("CommitteeTable")
	    .withFilterExpression("contains(userName, :val)")
	    .withProjectionExpression("userName, #n, email, logoUrl")
	    .withExpressionAttributeValues(expressionAttributeValues)
	    .withExpressionAttributeNames(expressionAttributeNames);
		
		ScanResult result = client.scan(scanRequest);
		return result.getItems();
	    
	}
	
	//For Committee
	public List<Map<String, AttributeValue>> searchComitteeByName(String searchText) {
		
		Map<String, AttributeValue> expressionAttributeValues =new HashMap<String, AttributeValue>();
			expressionAttributeValues.put(":val", new AttributeValue(searchText));
		Map<String, String> expressionAttributeNames = new HashMap<String, String>();
		expressionAttributeNames.put("#n", "name");
		
		ScanRequest scanRequest = new ScanRequest()
	    .withTableName("CommitteeTable")
	    .withFilterExpression("contains(#n, :val)")
	    .withProjectionExpression("userName, #n, email, logoUrl")
	    .withExpressionAttributeValues(expressionAttributeValues)
	    .withExpressionAttributeNames(expressionAttributeNames);
		
		ScanResult result = client.scan(scanRequest);
		return result.getItems();
	    
	}
	
	//For Committee
	public List<Map<String, AttributeValue>> searchComitteeByCollegeName(String searchText) {
		
		Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		expressionAttributeValues.put(":val", new AttributeValue(searchText));
		
		Map<String, String> expressionAttributeNames = new HashMap<String, String>();
		expressionAttributeNames.put("#n", "name");
		
		ScanRequest scanRequest = new ScanRequest()
	    .withTableName("CommitteeTable")
	    .withFilterExpression("contains(collegeName, :val)")
	    .withProjectionExpression("userName, #n, email, logoUrl")
	    .withExpressionAttributeValues(expressionAttributeValues)
		.withExpressionAttributeNames(expressionAttributeNames);
		
		ScanResult result = client.scan(scanRequest);
		return result.getItems();
	    
	}
    
}
