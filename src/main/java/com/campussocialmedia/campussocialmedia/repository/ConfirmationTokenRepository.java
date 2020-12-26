package com.campussocialmedia.campussocialmedia.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.campussocialmedia.campussocialmedia.entity.ConfirmationToken;

@Repository
public class ConfirmationTokenRepository {

	@Autowired
	private DynamoDBMapper mapper;

	public ConfirmationToken addConfirmationToken(ConfirmationToken token) {
		mapper.save(token);
		return token;
	}

	public ConfirmationToken findConfirmationTokenByEmail(String email) {
		return mapper.load(ConfirmationToken.class, email);
	}
	
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		return mapper.load(ConfirmationToken.class, confirmationToken);
	}

	public ConfirmationToken findConfirmationTokenByTokenId(String tokenId) {
		return mapper.load(ConfirmationToken.class, tokenId);
	}

	public String deleteConfirmationToken(ConfirmationToken token) {
		mapper.delete(token);
		return "ConfirmationToken Deleted";
	}

	/*
	public String updateToken(ConfirmationToken token) {
		mapper.save(token, buildExpression(token));
		//System.out.print("FOLLOWERS ADDED");
		return "Token Updated";
	}
	

	public ConfirmationToken updateConfirmationToken(ConfirmationToken token)
	{
		mapper.save(token, buildExpression(token));
		return token;
			
	}
	*/
	
	private DynamoDBSaveExpression buildExpression(ConfirmationToken token) {
		DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
		expectedMap.put("tokenid", new ExpectedAttributeValue(new AttributeValue().withS(token.getTokenid())));
		dynamoDBSaveExpression.setExpected(expectedMap);
		return dynamoDBSaveExpression;
	}
}
