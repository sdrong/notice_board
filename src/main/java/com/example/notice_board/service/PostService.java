package com.example.notice_board.service;

import com.example.notice_board.domain.Post;
import com.example.notice_board.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public void edit(Long id, Date date, String re_title, String re_detail){
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setDate(date);
            post.setTitle(re_title);
            post.setDetail(re_detail);
            postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with id: " + id);
        }
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

}
