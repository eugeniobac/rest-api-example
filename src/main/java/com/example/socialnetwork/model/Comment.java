package com.example.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private Long id;
    private Post post;
    private String name;
    private String email;
    private String body;

    public void setPostId(Long id){
        post = new Post();
        post.setId(id);
    }
}
