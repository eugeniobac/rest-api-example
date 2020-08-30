package com.example.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

    private Long id;
    private User user;
    private String title;
    private String body;
    private List<Comment> comments;

    public void setUserId(Long id){
        user = new User();
        user.setId(id);
    }
}
