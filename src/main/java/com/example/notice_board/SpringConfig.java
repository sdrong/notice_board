package com.example.notice_board;

import com.example.notice_board.repository.CommentRepository;
import com.example.notice_board.repository.JpaCommentRepository;
import com.example.notice_board.repository.JpaPostRepository;
import com.example.notice_board.repository.PostRepository;
import com.example.notice_board.service.CommentService;
import com.example.notice_board.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public PostService postService(){
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository(){
        return new JpaPostRepository(em);
    }

    @Bean
    public CommentService commentService() {return new CommentService(commentRepository());}

    @Bean
    public CommentRepository commentRepository() {return new JpaCommentRepository(em);}
}
