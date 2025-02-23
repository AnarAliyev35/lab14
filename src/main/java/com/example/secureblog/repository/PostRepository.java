package com.example.secureblog.repository;

import com.example.secureblog.model.Post;
import com.example.secureblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
    List<Post> findByUserId(Long userId);
}
