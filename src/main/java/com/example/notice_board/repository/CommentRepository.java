package com.example.notice_board.repository;

import com.example.notice_board.domain.Comment;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);

    List<Comment> findByPostId(Long postId);
}
