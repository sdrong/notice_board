package com.example.notice_board.controller;

import com.example.notice_board.domain.Comment;
import com.example.notice_board.domain.Post;
import com.example.notice_board.dto.CommentForm;
import com.example.notice_board.service.CommentService;
import com.example.notice_board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping(value = "/comments")
    public String Post(Model model, @RequestParam("id") Long id){
        List<Comment> comments = commentService.findByPostId(id);
        model.addAttribute("comments", comments);
        return "/comments";
    }

    @Transactional
    @PostMapping("/addComment")
    public String addComment(CommentForm commentForm) {
        Optional<Post> postOptional = postService.findById(commentForm.getPostId());
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            // 새 댓글을 생성하고 저장
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setDetail(commentForm.getDetail());
            commentService.save(comment);

            // 게시물 페이지로 리다이렉트
            return "redirect:/post?id=" + commentForm.getPostId();
        } else {
            return "redirect:/";
        }
    }
}
