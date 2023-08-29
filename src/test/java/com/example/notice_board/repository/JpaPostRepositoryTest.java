package com.example.notice_board.repository;

import com.example.notice_board.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaPostRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    private JpaPostRepository postRepository;

    @BeforeEach
    public void setUp() {
        postRepository = new JpaPostRepository(em);
    }

    @Test
    void save() {
        //given
        Post post = new Post();
        post.setDate(new Date());
        post.setTitle("테스트 제목");
        post.setDetail("테스트 내용");

        //when
        Post savePost = postRepository.save(post);
        Optional<Post> retrievedPost = postRepository.findById(savePost.getId());

        //then
        assertThat(retrievedPost.isPresent()).isTrue();
        assertThat(retrievedPost.get().getTitle()).isEqualTo("테스트 제목");
        assertThat(retrievedPost.get().getDetail()).isEqualTo("테스트 내용");
    }

    @Test
    void edit() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }
}