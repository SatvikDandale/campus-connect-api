package com.campussocialmedia.campussocialmedia.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.campussocialmedia.campussocialmedia.entity.Comment;
import com.campussocialmedia.campussocialmedia.entity.Post;
import com.campussocialmedia.campussocialmedia.entity.PostCreationRequest;
import com.campussocialmedia.campussocialmedia.repository.PostRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MediaService mediaService;

    private Post convertToPost(PostCreationRequest post) {
        return modelMapper.map(post, Post.class);
    }

    /*
     * Creating a Post is pretty straight-forward. Convert PostCreationRequest
     * object to the actual Post Object and then save it in the database. But we
     * also need to check if the user has provided a file in the CreationObject. If
     * yes, we need to upload it using "MediaService" and add the URL into the new
     * Post object.
     */
    public Post addPost(PostCreationRequest post) {
        Post convertedPost = convertToPost(post);
        // Set<String> t = new HashSet<String>();
        // t.add(convertedPost.getUserName());
        // convertedPost.setLikes(t);
        // convertedPost.setLikes(new HashSet<String>());
        // convertedPost.setLikes(new HashSet<String>());
        if (post.getFile() != null) {
            // Upload this file.
            String url = mediaService.uploadFile(post.getFile());
            convertedPost.setUrl(url);
        }
        // System.out.println("Hello");
        // System.out.println(convertedPost);

        return repository.addPost(convertedPost);
    }

    public Post findPostByID(String postID) {
        return repository.findPostByID(postID);
    }

    public List<Post> findPostsByUserName(String userName) {
        return repository.findPostsByUserName(userName);
    }

    public List<Post> findAllPostsByUserNames(List<String> userNames, String requester) {
        List<Post> posts = new ArrayList<Post>();
        for (String userName : userNames) {
            posts.addAll(this.findPostsByUserName(userName));
        }
        posts.addAll(this.findPostsByUserName(requester));
        Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                return p1.getTimeStamp().compareTo(p2.getTimeStamp()) * -1;
            }
        });
        for (Post post : posts) {
            System.out.println(post.getTimeStamp());
        }
        return posts;
    }


    public void addLikeToPost(String userName, String postID) {
        Post post = repository.findPostByID(postID);
        if (post.getLikes() == null) {
            Set<String> t = new HashSet<String>();
            t.add(userName);
            post.setLikes(t);
        } else {
            Set<String> likeSet = post.getLikes();
            System.out.println(likeSet);
            likeSet.add(userName);
            post.setLikes(likeSet);
        }

        repository.updatePost(post);
    }

    public void removeLike(String userName, String postID) {
        Post post = repository.findPostByID(postID);

        if (post.getLikes() == null)
            return;

        Set<String> likeSet = post.getLikes();
        if (likeSet.contains(userName))
            likeSet.remove(userName);
        else
            return;
        if (likeSet.size() == 0)
            post.setLikes(null);
        else
            post.setLikes(likeSet);
        repository.updatePost(post);

    }

    public void addCommentToPost(String name, String postID, String comment) {
        Post post = repository.findPostByID(postID);
        List<Comment> commentList = post.getComments();
        Comment commentObj = new Comment(name, comment);
        commentList.add(commentObj);
        post.setComments(commentList);
        repository.updatePost(post);
    }

}
