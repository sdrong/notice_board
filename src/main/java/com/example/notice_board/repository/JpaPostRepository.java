package com.example.notice_board.repository;

import com.example.notice_board.domain.Comment;
import com.example.notice_board.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaPostRepository implements PostRepository{
    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post save(Post post) {
        em.persist(post);//데이터베이스에 post내용 영속화
        return post;
    }

    @Override
    public Post edit(Long id, Date date, String re_detail) {
        Post post = em.find(Post.class, id);
        if(post != null){
            post.setDetail(re_detail);
            post.setDate(date);
            em.merge(post);
        }
        return post;
    }

    @Override
    public void deleteById(Long id) {
        Post post = em.find(Post.class, id);
        if(post != null){
            em.remove(post);
        }
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();
    }
}
