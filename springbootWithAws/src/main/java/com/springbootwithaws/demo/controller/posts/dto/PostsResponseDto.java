package com.springbootwithaws.demo.controller.posts.dto;

import com.springbootwithaws.demo.domain.posts.Posts;

public class PostsResponseDto {
    private String title;
    private String content;
    private String author;


    public PostsResponseDto(Posts posts) {
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}
