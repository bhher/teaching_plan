package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.domain.Post;
import com.example.thymeleafdemo.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    private final PostService postService;

    public PageController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Thymeleaf 데모");
        return "home";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        return postService.findById(id)
                .map((Post post) -> {
                    model.addAttribute("post", post);
                    return "post-detail";
                })
                .orElse("redirect:/posts");
    }
}
