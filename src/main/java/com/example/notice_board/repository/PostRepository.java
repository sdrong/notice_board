package com.example.notice_board.repository;

import com.example.notice_board.domain.Comment;
import com.example.notice_board.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface PostRepository {
    Post save(Post post);

    Post edit(Long id, Date date, String re_detail);

    void deleteById(Long id);

    Optional<Post> findById(Long id);

    List<Post> findAll();

    List<Comment> findByPostId(Long postId);
}
