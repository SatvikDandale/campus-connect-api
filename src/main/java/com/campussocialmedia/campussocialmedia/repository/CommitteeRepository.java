package com.campussocialmedia.campussocialmedia.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.campussocialmedia.campussocialmedia.entity.Committee;
import com.campussocialmedia.campussocialmedia.entity.CommitteeMembers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommitteeRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public Committee findCommitteeByUserName(String userName){
        return mapper.load(Committee.class, userName);
    }

    public Committee addCommittee(Committee committee){
        mapper.save(committee);
        return committee;
    }

    public Committee updateCommitteeAboutDetails(Committee committee) {
        mapper.save(committee, buildExpression(committee));
        return committee;
    }

    public void updateCommitteeDatabase(Committee committee){
        mapper.save(committee, buildExpression(committee));
    }

    public List<String> getCommitteeFollowers(String userName){
        Committee committee = mapper.load(Committee.class, userName);
        return committee.getFollowers();
    }

    public List<CommitteeMembers> getCommitteeMembersRepo(String userName){
        Committee committee = mapper.load(Committee.class, userName);
        return committee.getCommitteeMembers();
    }

    private DynamoDBSaveExpression buildExpression(Committee committee) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("userName", new ExpectedAttributeValue(new AttributeValue().withS(committee.getUserName())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
