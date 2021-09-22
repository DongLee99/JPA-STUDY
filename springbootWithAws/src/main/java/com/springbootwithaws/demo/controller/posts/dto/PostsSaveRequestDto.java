package com.springbootwithaws.demo.controller.posts.dto;

import com.springbootwithaws.demo.domain.posts.Posts;
import lombok.Builder;

public class PostsSaveRequestDto {
    private final String title;
    private final String content;
    private final String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
