package com.example.socialnetwork.system;

import com.example.socialnetwork.model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
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
public class PostIntegrationTest {

    public static final String ALL_POSTS_ENDPOINT = "all-posts-endpoint";
    public static final String GET_POST_ENDPOINT = "get-post-endpoint";
    @Spy
    private PostIntegration postIntegration;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(postIntegration, "allPostsEndpoint", ALL_POSTS_ENDPOINT);
        ReflectionTestUtils.setField(postIntegration, "getPostEndpoint", GET_POST_ENDPOINT);

        when(postIntegration.getRestTemplate()).thenReturn(restTemplate);
    }

    @Test
    public void getPosts(){
        when(restTemplate.getForEntity(ALL_POSTS_ENDPOINT, Post[].class)).thenReturn(ResponseEntity.ok(new Post[]{}));

        Assert.assertTrue(postIntegration.getPosts().isEmpty());

        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        posts.add(new Post());

        when(restTemplate.getForEntity(ALL_POSTS_ENDPOINT, Post[].class)).thenReturn(ResponseEntity.ok(posts.toArray(new Post[posts.size()])));
        Assert.assertEquals(2, postIntegration.getPosts().size());
    }

    @Test
    public void getPostById(){
        when(restTemplate.getForEntity(eq(GET_POST_ENDPOINT), eq(Post.class), anyLong())).thenReturn(ResponseEntity.ok(new Post()));
        Assert.assertNotNull(postIntegration.getPost(1l));
    }
}
