package com.campussocialmedia.campussocialmedia.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.campussocialmedia.campussocialmedia.entity.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public Comment addComment(Comment comment) {
        mapper.save(comment);
        System.out.println(comment);
        return comment;
    }

    public Comment findCommentByID(String commentID) {
        return mapper.load(Comment.class, commentID);
    }

    /*
     * The Comments table has a GLOBAL SECONDARY INDEX named PostIndex. This index has
     * "postID" as the hash key so that we can get all the comments using this new
     * hashKey. NOTE: In GSI, the hashKey need not to be unique, i.e. multiple comments
     * can have same postID. But the main index hashKey, i.e. commentID, must be
     * unique.
     */

    public List<Comment> findCommentsByPostID(String postID) {
        final Comment comment = new Comment();
        comment.setPostID(postID);
        final DynamoDBQueryExpression<Comment> queryExpression = new DynamoDBQueryExpression<>();
        queryExpression.setHashKeyValues(comment);
        queryExpression.setIndexName("PostIndex");
        // GSI Name
        queryExpression.setConsistentRead(false);
        // Cannot use consistent read on GSI
        final PaginatedQueryList<Comment> results = mapper.query(Comment.class, queryExpression);
        /*
         * The PaginatedQueryList is a lazy loader list. If we retrieve only 'x' amount
         * of results from it, it will fetch only 'x' results from the database. Like
         * pagination.
         */

        // All the results
        return results.subList(0, results.size());
    }

    public void updateComment(Comment comment)
    {
        mapper.save(comment, buildExpression(comment));
    }


    private DynamoDBSaveExpression buildExpression(Comment comment) {
		DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
		expectedMap.put("commentID", new ExpectedAttributeValue(new AttributeValue().withS(comment.getCommentID())));
		dynamoDBSaveExpression.setExpected(expectedMap);
		return dynamoDBSaveExpression;
    }
    
    public void removeComment(String commentID)
    {
        Comment comment = new Comment();
        comment.setCommentID(commentID);
        mapper.delete(comment);
    }

}
