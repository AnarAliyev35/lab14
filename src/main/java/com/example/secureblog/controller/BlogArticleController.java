package com.example.secureblog.controller;

import com.example.secureblog.model.BlogArticle;
import com.example.secureblog.service.BlogArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class BlogArticleController {

    private final BlogArticleService blogArticleService;

    public BlogArticleController(BlogArticleService blogArticleService) {
        this.blogArticleService = blogArticleService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public BlogArticle createArticle(@RequestParam String title, @RequestParam String content) {
        return blogArticleService.createArticle(title, content);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<BlogArticle> getUserArticles() {
        return blogArticleService.getUserArticles();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        blogArticleService.deleteArticle(id);
    }
}
