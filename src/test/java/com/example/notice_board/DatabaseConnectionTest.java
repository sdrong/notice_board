package com.example.notice_board;

import com.example.notice_board.domain.Post;
import com.example.notice_board.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//스프링 부트의 JPA 테스트 환경을 사용하여 테스트
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false) // 테스트 후 롤백하지 않도록 설정
public class DatabaseConnectionTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testDatabaseConnection() {
        // 새로운 Post 객체 생성 및 저장
        Post post = new Post();
        post.setTitle("Test Title");
        post.setDetail("Test Detail");
        postRepository.save(post);

        // 저장한 데이터 조회
        List<Post> posts = postRepository.findAll();

        // 조회한 데이터가 예상과 일치하는지 확인
        assertThat(posts).isNotEmpty();
        assertThat(posts.get(0).getTitle()).isEqualTo("Test Title");
        assertThat(posts.get(0).getDetail()).isEqualTo("Test Detail");
    }
}
