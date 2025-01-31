package com.example.secureblog.service;

import com.example.secureblog.model.BlogArticle;
import com.example.secureblog.model.User;
import com.example.secureblog.repository.BlogArticleRepository;
import com.example.secureblog.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogArticleService {

    private final BlogArticleRepository blogArticleRepository;
    private final UserRepository userRepository;

    public BlogArticleService(BlogArticleRepository blogArticleRepository, UserRepository userRepository) {
        this.blogArticleRepository = blogArticleRepository;
        this.userRepository = userRepository;
    }

    public BlogArticle createArticle(String title, String content) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BlogArticle article = new BlogArticle();
        article.setTitle(title);
        article.setContent(content);
        article.setUser(user);
        return blogArticleRepository.save(article);
    }

    public List<BlogArticle> getUserArticles() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return blogArticleRepository.findByUserId(user.getId());
    }

    public void deleteArticle(Long id) {
        Optional<BlogArticle> article = blogArticleRepository.findById(id);
        article.ifPresent(blogArticleRepository::delete);
    }
}
