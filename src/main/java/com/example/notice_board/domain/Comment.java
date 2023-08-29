package com.example.notice_board.domain;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String detail;

    public Comment() {
    }

    public Comment(Long id, Post post, String detail) {
        this.id = id;
        this.post = post;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
