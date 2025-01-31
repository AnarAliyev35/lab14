package com.example.secureblog.repository;

import com.example.secureblog.model.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BlogArticleRepository extends JpaRepository<BlogArticle, Long> {
    List<BlogArticle> findByUserId(Long userId);
}
