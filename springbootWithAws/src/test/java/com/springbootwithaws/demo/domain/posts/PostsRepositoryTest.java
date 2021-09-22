package com.springbootwithaws.demo.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "응애";
        String content = "애기 동현";

        postsRepository.save(Posts.builder()
                                    .title(title)
                                    .content(content)
                                    .author("jhnj741@gmail.com")
                                    .build());
        //when
        List<Posts> postsList = postsRepository.findAll();
        //then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo("응애");
    }
}