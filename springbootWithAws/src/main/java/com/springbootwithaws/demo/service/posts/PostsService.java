package com.springbootwithaws.demo.service.posts;

import com.springbootwithaws.demo.controller.posts.dto.PostsListResponseDto;
import com.springbootwithaws.demo.controller.posts.dto.PostsResponseDto;
import com.springbootwithaws.demo.controller.posts.dto.PostsSaveRequestDto;
import com.springbootwithaws.demo.controller.posts.dto.PostsUpdateRequestDto;
import com.springbootwithaws.demo.domain.posts.Posts;
import com.springbootwithaws.demo.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 게시글이 없음"));
        return new PostsResponseDto(posts);
    }

    @Transactional
    public List<PostsListResponseDto> findByAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(post -> new PostsListResponseDto(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts  = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없음"));
        postsRepository.delete(posts);
    }
}
