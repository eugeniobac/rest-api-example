package com.example.socialnetwork.service;

import com.example.socialnetwork.model.Comment;
import com.example.socialnetwork.model.Post;
import com.example.socialnetwork.system.CommentIntegration;
import com.example.socialnetwork.system.PostIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostIntegration postIntegration;

    @Autowired
    private CommentIntegration commentIntegration;

    public List<Post> getPosts() {
        List<Post> posts = getPostIntegration().getPosts();

        /**
         * In this case, as we're listing all the posts along with all the comments.
         * It's better to get all the comments in one go (single api call) and do the mapping in the code
         * It will increase the performance and it also would decrease any api threshold (if there is one)
         */
        List<Comment> allComments = getCommentIntegration().getAllComments();
        loadPostComments(posts, allComments);

        return posts;
    }

    private void loadPostComments(List<Post> posts, List<Comment> allComments) {
        posts.forEach(post -> post.setComments(getComments(post, allComments)));
    }

    private List<Comment> getComments(Post post, List<Comment> allComments) {
        return allComments.stream()
                          .filter(comment -> comment.getPost().getId().equals(post.getId()))
                          .collect(Collectors.toList());
    }

    public Post getPost(Long id) {
        Post post = getPostIntegration().getPost(id);

        List<Comment> comments = getCommentIntegration().getAllComments(id);
        post.setComments(comments);

        return post;
    }

    protected PostIntegration getPostIntegration() {
        return postIntegration;
    }

    protected CommentIntegration getCommentIntegration() {
        return commentIntegration;
    }
}
