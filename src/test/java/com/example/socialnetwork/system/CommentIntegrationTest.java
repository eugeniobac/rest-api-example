package com.example.socialnetwork.system;


import com.example.socialnetwork.model.Comment;
import com.example.socialnetwork.model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentIntegrationTest {
    public static final String ALL_COMMENTS_ENDPOINT = "all-comments-endpoint";
    public static final String GET_POST_COMMENTS_ENDPOINT = "get-post-comments-endpoint";

    @Spy
    private CommentIntegration commentIntegration;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(commentIntegration, "commentsEndpoint", ALL_COMMENTS_ENDPOINT);
        ReflectionTestUtils.setField(commentIntegration, "postCommentsEndpoint", GET_POST_COMMENTS_ENDPOINT);

        when(commentIntegration.getRestTemplate()).thenReturn(restTemplate);
    }

    @Test
    public void getAllComments(){
        when(restTemplate.getForEntity(ALL_COMMENTS_ENDPOINT, Comment[].class)).thenReturn(ResponseEntity.ok(new Comment[]{}));

        Assert.assertTrue(commentIntegration.getAllComments().isEmpty());

        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());

        when(restTemplate.getForEntity(ALL_COMMENTS_ENDPOINT, Comment[].class))
                .thenReturn(ResponseEntity.ok(comments.toArray(new Comment[comments.size()])));

        Assert.assertEquals(2, commentIntegration.getAllComments().size());
    }

    @Test
    public void getCommentsByPostId(){
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());

        when(restTemplate.getForEntity(eq(GET_POST_COMMENTS_ENDPOINT), eq(Comment[].class), anyLong()))
                .thenReturn(ResponseEntity.ok(comments.toArray(new Comment[comments.size()])));

        Assert.assertNotNull(commentIntegration.getAllComments(1l));
    }
}
