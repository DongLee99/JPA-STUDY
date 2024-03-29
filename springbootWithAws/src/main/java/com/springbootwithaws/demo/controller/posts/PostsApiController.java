package com.springbootwithaws.demo.controller.posts;

import com.springbootwithaws.demo.controller.posts.dto.PostsResponseDto;
import com.springbootwithaws.demo.controller.posts.dto.PostsSaveRequestDto;
import com.springbootwithaws.demo.controller.posts.dto.PostsUpdateRequestDto;
import com.springbootwithaws.demo.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/api/v1/posts")
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id ,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/find/dec")
    public List<PostsResponseDto> findByIdDesc(@PathVariable Long id) {
        return null;
    }
    @DeleteMapping("/api/v1/posts/{id}")
    public Long postsDelete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
