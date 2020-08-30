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
public class PostIntegration {
    private Logger logger = LoggerFactory.getLogger(PostIntegration.class);

    @Value("${system.integration.endpoint.all.posts}")
    private String allPostsEndpoint;

    @Value("${system.integration.endpoint.get.post}")
    private String getPostEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    public List<Post> getPosts() {
        logger.info("Calling endpoint: " + allPostsEndpoint);
        Post[] body = getRestTemplate().getForEntity(allPostsEndpoint, Post[].class).getBody();

        return Arrays.asList(body);
    }

    public Post getPost(Long id) {
        logger.info("Calling endpoint: " + allPostsEndpoint);
        Post body = getRestTemplate().getForEntity(getPostEndpoint, Post.class, id).getBody();

        return body;
    }

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
