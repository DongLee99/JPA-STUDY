package com.springbootwithaws.demo.controller.posts.dto;


import com.springbootwithaws.demo.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsUpdateRequestDto {
    private final String title;
    private final String content;
    private final String author;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
