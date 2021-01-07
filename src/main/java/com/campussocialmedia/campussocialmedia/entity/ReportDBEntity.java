package com.campussocialmedia.campussocialmedia.entity;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "ReportedPosts")
public class ReportDBEntity {
	
	private String collegeName;
	private HashMap<String, Integer> postIDs;
	
	public ReportDBEntity() {
		super();
	}
	
	public ReportDBEntity(String collegeName, HashMap<String, Integer> postIDs) {
		super();
		this.collegeName = collegeName;
		this.postIDs = postIDs;
	}
	
	@DynamoDBHashKey(attributeName = "collegeName")
	public String getCollegeName() {
		return collegeName;
	}
	
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	@DynamoDBAttribute
	public HashMap<String, Integer> getPostIDs() {
		return postIDs;
	}
	
	public void setPostIDs(HashMap<String, Integer> postIDs) {
		this.postIDs = postIDs;
	}
	
	@Override
	public String toString() {
		return "ReportDBEntity [collegeName=" + collegeName + ", postIDs=" + postIDs + "]";
	}
	
	
	
	/*
	private String collegeName;
	private List<String> postId;
	
	public ReportDBEntity() {
		super();
	}

	public ReportDBEntity(String collegeName, List<String> postId) {
		super();
		this.collegeName = collegeName;
		this.postId = postId;
	}

	@DynamoDBHashKey(attributeName = "collegeName")
	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@DynamoDBAttribute
	public List<String> getPostId() {
		return postId;
	}

	public void setPostId(List<String> postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "ReportDBEntity [collegeName=" + collegeName + ", postId=" + postId + "]";
	}
	*/

}
