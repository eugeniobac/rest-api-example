package com.example.socialnetwork.service;

import com.example.socialnetwork.model.Comment;
import com.example.socialnetwork.model.Post;
import com.example.socialnetwork.system.CommentIntegration;
import com.example.socialnetwork.system.PostIntegration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Spy
    private PostService postService;

    @MockBean
    private CommentIntegration commentIntegrationMock;

    @MockBean
    private PostIntegration postIntegrationMock;

    @Before
    public void setUp(){
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post();
        post1.setId(1l);

        Post post2 = new Post();
        post2.setId(2l);

        posts.add(post1);
        posts.add(post2);

        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setPostId(1l);
        comments.add(comment1);

        when(postService.getCommentIntegration()).thenReturn(commentIntegrationMock);
        when(postService.getPostIntegration()).thenReturn(postIntegrationMock);

        when(postIntegrationMock.getPosts()).thenReturn(posts);
        when(commentIntegrationMock.getAllComments()).thenReturn(comments);

        when(postIntegrationMock.getPost(Mockito.any())).thenReturn(new Post());
    }

    @Test
    public void testGetAllPosts(){
        List<Post> posts = postService.getPosts();
        assertEquals(2, posts.size());
    }

    @Test
    public void testGetAllPostsWithComments(){
        List<Post> posts = postService.getPosts();
        Assert.assertTrue(!posts.isEmpty());

        Post post1 = posts.stream().filter(post -> post.getId().equals(1l)).findFirst().get();
        Post post2 = posts.stream().filter(post -> post.getId().equals(2l)).findFirst().get();

        assertEquals(1, post1.getComments().size());
        assertEquals(0, post2.getComments().size());
    }

    @Test
    public void testGetSinglePost(){
        Post post = postService.getPost(1l);
        assertNotNull(post);
    }

}
