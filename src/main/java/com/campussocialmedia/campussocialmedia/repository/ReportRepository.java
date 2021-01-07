package com.campussocialmedia.campussocialmedia.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.campussocialmedia.campussocialmedia.entity.ReportDBEntity;
import com.campussocialmedia.campussocialmedia.entity.UserDBEntity;

@Repository
public class ReportRepository {

	@Autowired
	private DynamoDBMapper mapper;
	
    public ReportDBEntity addReport(ReportDBEntity reportDbBEntity) {
		mapper.save(reportDbBEntity);
		return reportDbBEntity;
	}
    
	public ReportDBEntity findByCollegeName(String collegeName) {
		return mapper.load(ReportDBEntity.class, collegeName);
	}

	public String deleteReportEntity(ReportDBEntity college) {
		mapper.delete(college);
		return "College Deleted";
	}

	public String updateReportEntity(ReportDBEntity reportDbBEntity) {
		mapper.save(reportDbBEntity, buildExpression(reportDbBEntity));
		return "User Updated";
	}

	private DynamoDBSaveExpression buildExpression(ReportDBEntity user) {
		DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
		expectedMap.put("collegeName", new ExpectedAttributeValue(new AttributeValue().withS(user.getCollegeName())));
		dynamoDBSaveExpression.setExpected(expectedMap);
		return dynamoDBSaveExpression;
	}
}
