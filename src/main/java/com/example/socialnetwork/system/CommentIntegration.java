package com.example.socialnetwork.system;

import com.example.socialnetwork.model.Comment;
import com.example.socialnetwork.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentIntegration {
    private Logger logger = LoggerFactory.getLogger(CommentIntegration.class);

    @Value("${system.integration.endpoint.post.comments}")
    private String postCommentsEndpoint;

    @Value("${system.integration.endpoint.all.comments}")
    private String commentsEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    public List<Comment> getAllComments(Long postId){
        logger.info("Calling endpoint: " + postCommentsEndpoint);
        Comment[] body = getRestTemplate().getForEntity(postCommentsEndpoint, Comment[].class, postId).getBody();

        return Arrays.asList(body);
    }

    public List<Comment> getAllComments(){
        Comment[] body = getRestTemplate().getForEntity(commentsEndpoint, Comment[].class).getBody();

        return Arrays.asList(body);
    }

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
