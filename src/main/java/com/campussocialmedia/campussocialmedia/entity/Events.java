package com.campussocialmedia.campussocialmedia.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Events")
public class Events {
    // private static final long serialVersionUID = 1L;

    
    //primary index
    private String eventID;

    //secondary index
    private String committeeUserName; //committee user name to which this event belongs
    
    private String title; //name of the event
    private String description; //short description about the event
    private EventStats eventStats; 
    private List<String> winners; 
    private EventOrganisers eventOrganisers;
    private String coverPicUrl;
    // private EventDates dates;
    private String startDate; //event start date
    private String endDate; //event end date

    public Events() {
    }

	public Events(String eventID, String title, String committeeUserName, String description, EventStats eventStats,
			List<String> winners, EventOrganisers eventOrganisers, String coverPicUrl, String startDate,
			String endDate) {
		this.eventID = eventID;
		this.title = title;
		this.committeeUserName = committeeUserName;
		this.description = description;
		this.eventStats = eventStats;
		this.winners = winners;
		this.eventOrganisers = eventOrganisers;
		this.coverPicUrl = coverPicUrl;
		this.startDate = startDate;
		this.endDate = endDate;
	}

    @DynamoDBHashKey(attributeName = "eventID")
    @DynamoDBAutoGeneratedKey
	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

    @DynamoDBAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// @DynamoDBIndexHashKey(globalSecondaryIndexName = "UserIndex")
	@DynamoDBAttribute
	public String getCommitteeUserName() {
		return committeeUserName;
	}

	public void setCommitteeUserName(String committeeUserName) {
		this.committeeUserName = committeeUserName;
	}

    @DynamoDBAttribute
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @DynamoDBAttribute
	public EventStats getEventStats() {
		return eventStats;
	}

	public void setEventStats(EventStats eventStats) {
		this.eventStats = eventStats;
	}

    @DynamoDBAttribute
	public List<String> getWinners() {
		return winners;
	}

	public void setWinners(List<String> winners) {
		this.winners = winners;
	}

    @DynamoDBAttribute
	public EventOrganisers getEventOrganisers() {
		return eventOrganisers;
	}

	public void setEventOrganisers(EventOrganisers eventOrganisers) {
		this.eventOrganisers = eventOrganisers;
	}

    @DynamoDBAttribute
	public String getCoverPicUrl() {
		return coverPicUrl;
	}

	public void setCoverPicUrl(String coverPicUrl) {
		this.coverPicUrl = coverPicUrl;
	}

    @DynamoDBAttribute
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

    @DynamoDBAttribute
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

    @Override
    public String toString() {
        return "Events [committeeUserName=" + committeeUserName + ", coverPicUrl=" + coverPicUrl + ", description="
                + description + ", endDate=" + endDate + ", eventID=" + eventID + ", eventOrganisers=" + eventOrganisers
                + ", eventStats=" + eventStats + ", startDate=" + startDate + ", title=" + title + ", winners="
                + winners + "]";
    } 
}
