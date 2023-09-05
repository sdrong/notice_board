package com.example.notice_board.domain;

import javax.persistence.*;

@Entity //jpa엔티티임을 나타냄
@Table(name = "comment")
public class Comment {
    @Id //primary key임을 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id가 자동생성 데이터 베이스가 자동으로 값 생성
    private Long id;

    @ManyToOne // post엔티티와 다대일 매핑
    @JoinColumn(name = "post_id") // comment와 post간의 관계 표현
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String detail;

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