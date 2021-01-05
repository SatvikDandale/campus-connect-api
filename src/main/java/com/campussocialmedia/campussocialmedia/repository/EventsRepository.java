package com.campussocialmedia.campussocialmedia.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.campussocialmedia.campussocialmedia.entity.EventCard;
import com.campussocialmedia.campussocialmedia.entity.Events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventsRepository {
    private static final String TABLE_NAME = "Events";

    @Autowired
    private DynamoDBMapper mapper;

    public Events findEventById(String eventID){
        return mapper.load(Events.class, eventID);
    }

    public List<Events> findEventsByCommitteeUserName(String committeeUserName) {
        final Events event = new Events();
        event.setCommitteeUserName(committeeUserName);
        final DynamoDBQueryExpression<Events> queryExpression = new DynamoDBQueryExpression<>();
        queryExpression.setHashKeyValues(event);
        queryExpression.setIndexName("UserIndex");
        // GSI Name
        queryExpression.setConsistentRead(false);
        // Cannot use consistent read on GSI
        final List<Events> events = mapper.query(Events.class, queryExpression);
        
        // All the results
        return events;
    }

    
    
}
