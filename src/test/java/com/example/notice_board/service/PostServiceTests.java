package com.example.notice_board.service;

import com.example.notice_board.domain.Post;
import com.example.notice_board.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@Transactional
class PostServiceTests {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Test
    public void 게시물추가() throws Exception{
        // Given
        Post post = new Post();
        post.setTitle("Test Title");
        post.setDetail("Test Detail");
        Date currentDate = new Date();
        post.setDate(currentDate);

        // When
        Post savedPost = postService.save(post);

        // Then
        Assertions.assertThat(savedPost.getId()).isNotNull();
        Assertions.assertThat(savedPost.getTitle()).isEqualTo("Test Title");
        Assertions.assertThat(savedPost.getDetail()).isEqualTo("Test Detail");
        Assertions.assertThat(savedPost.getDate()).isEqualTo(currentDate);
    }
}
