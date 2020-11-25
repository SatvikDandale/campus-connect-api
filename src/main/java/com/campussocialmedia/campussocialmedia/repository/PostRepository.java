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
import com.campussocialmedia.campussocialmedia.entity.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public Post addPost(Post post) {
        System.out.println(post);
        mapper.save(post);
        return post;
    }

    public Post findPostByID(String postID) {
        return mapper.load(Post.class, postID);
    }

    /*
     * The Posts table has a GLOBAL SECONDARY INDEX named UserIndex This index has
     * "userName" as the hash key so that we can get all the posts using this new
     * hashKey. NOTE: In GSI, the hashKey need not to be unique, i.e. multiple posts
     * can have same userName. But the main index hashKey, i.e. postID, must be
     * unique.
     */

    public List<Post> findPostsByUserName(String userName) {
        final Post post = new Post();
        post.setUserName(userName);
        final DynamoDBQueryExpression<Post> queryExpression = new DynamoDBQueryExpression<>();
        queryExpression.setHashKeyValues(post);
        queryExpression.setIndexName("UserIndex");
        // GSI Name
        queryExpression.setConsistentRead(false);
        // Cannot use consistent read on GSI
        final PaginatedQueryList<Post> results = mapper.query(Post.class, queryExpression);
        /*
         * The PaginatedQueryList is a lazy loader list. If we retrieve only 'x' amount
         * of results from it, it will fetch only 'x' results from the database. Like
         * pagination.
         */

        // All the results
        return results.subList(0, results.size());
    }

    public void updatePost(Post post)
    {
        mapper.save(post, buildExpression(post));
    }


    private DynamoDBSaveExpression buildExpression(Post post) {
		DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
		expectedMap.put("userName", new ExpectedAttributeValue(new AttributeValue().withS(post.getUserName())));
		dynamoDBSaveExpression.setExpected(expectedMap);
		return dynamoDBSaveExpression;
	}
}
