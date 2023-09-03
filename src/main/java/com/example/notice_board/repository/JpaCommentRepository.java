package com.example.notice_board.repository;

import com.example.notice_board.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JpaCommentRepository implements CommentRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return em.createQuery("select c from Comment c WHERE c.post.id = :postId", Comment.class)
                .setParameter("postId", postId)
                .getResultList();
    }
}
