package com.example.notice_board;

import com.example.notice_board.repository.JpaPostRepository;
import com.example.notice_board.repository.PostRepository;
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
    public PostRepository postRepository(){
        return new JpaPostRepository(em);
    }
}
