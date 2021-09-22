package com.springbootwithaws.demo.service.posts;

import com.springbootwithaws.demo.controller.posts.dto.PostsResponseDto;
import com.springbootwithaws.demo.controller.posts.dto.PostsSaveRequestDto;
import com.springbootwithaws.demo.controller.posts.dto.PostsUpdateRequestDto;
import com.springbootwithaws.demo.domain.posts.Posts;
import com.springbootwithaws.demo.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        posts.update(requestDto);
        return posts.getId();
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 게시글이 없음"));
        return new PostsResponseDto(posts);
    }
}
