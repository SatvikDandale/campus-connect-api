package com.campussocialmedia.campussocialmedia.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.campussocialmedia.campussocialmedia.entity.EventAdd;
import com.campussocialmedia.campussocialmedia.entity.Events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventsRepository {
    private static final String TABLE_NAME = "Events";

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private AmazonDynamoDB client;


    public Events findEventById(String eventID){
        return mapper.load(Events.class, eventID);
    }

    public Events addEventRepo(Events event){
        try{
            // event.setEventID("90");
            // System.out.println(event);
            mapper.save(event);
            return event;
        } catch(Exception e){
            System.out.println("Error Adding event in repo");
            return new Events();
        }
    }
    

    public List<Map<String, AttributeValue>> getPastEventsRepo(String committeeUserName) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":val1", new AttributeValue(committeeUserName));
        expressionAttributeValues.put(":val2", new AttributeValue(strDate));

        ScanRequest scanRequest = new ScanRequest().withTableName("Events")
                .withFilterExpression("(committeeUserName = :val1) and (startDate < :val2)")
                .withProjectionExpression("eventID, committeeUserName ,title, description")
                .withExpressionAttributeValues(expressionAttributeValues);
        ScanResult result = client.scan(scanRequest);
        return result.getItems();

    }

    public List<Map<String, AttributeValue>> getUpcomingEventsRepo(String committeeUserName) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":val1", new AttributeValue(committeeUserName));
        expressionAttributeValues.put(":val2", new AttributeValue(strDate));

        ScanRequest scanRequest = new ScanRequest().withTableName("Events")
                .withFilterExpression("(committeeUserName = :val1) and (startDate > :val2)")
                .withProjectionExpression("eventID, committeeUserName ,title, description")
                .withExpressionAttributeValues(expressionAttributeValues);
        ScanResult result = client.scan(scanRequest);
        return result.getItems();

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
