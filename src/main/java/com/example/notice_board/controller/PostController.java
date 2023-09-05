package com.example.notice_board.controller;

import com.example.notice_board.domain.Comment;
import com.example.notice_board.domain.Post;
import com.example.notice_board.dto.PostForm;
import com.example.notice_board.service.CommentService;
import com.example.notice_board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController  {

    private final PostService postService;

    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String board(Model model){
        List<Post> postList = postService.findAll();
        model.addAttribute("postList", postList);
        return "board";
    }

    @GetMapping(value = "/post")
    public String post(Model model, @RequestParam("id") Long id){
        Optional<Post> post = postService.findById(id);
        if(post.isPresent()){
            model.addAttribute("post", post.get());
            List<Comment> comments = commentService.findByPostId(id);
            model.addAttribute("comments", comments);

            return "/post";
        }
        else{
            return "redirect:/"; // 메모가 없는 경우 목록 페이지로 리다이렉트합니다.
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id") Long id){
        postService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/addPost")
    public String addPage(){
        return "/newPost";
    }

    @PostMapping(value = "/addPost")
    public String addPost(PostForm postForm){
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setDetail(postForm.getDetail());
        Date currentDate = new Date(System.currentTimeMillis());
        post.setDate(currentDate);
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam("id") Long id){
        Optional<Post> post = postService.findById(id);

        if(post.isPresent()){
            model.addAttribute("post", post.get());
            return "/editPost";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/edit")
    public String editPost(@RequestParam("id") Long id, @RequestParam("title") String newTitle, @RequestParam("detail") String newDetail){
        Date currentDate = new Date(System.currentTimeMillis());
        postService.edit(id, currentDate, newTitle, newDetail);
        return "redirect:/";
    }
}
