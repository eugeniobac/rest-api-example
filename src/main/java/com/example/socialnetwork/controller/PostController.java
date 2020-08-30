package com.example.socialnetwork.controller;

import com.example.socialnetwork.model.Post;
import com.example.socialnetwork.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService service;

    @ResponseBody
    @GetMapping("/posts")
    public List<Post> getPosts(){
        return service.getPosts();
    }

    @ResponseBody
    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable(required = true, name = "id") Long id){
        return service.getPost(id);
    }

}
