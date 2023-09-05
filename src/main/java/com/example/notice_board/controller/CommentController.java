package com.example.notice_board.controller;

import com.example.notice_board.domain.Comment;
import com.example.notice_board.service.CommentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/post")
    public String PostComments(Model model, @RequestParam("id") Long id){
        List<Comment> comments = commentService.findByPostId(id);
        model.addAttribute("comments", comments);
        return "post";
    }
}
