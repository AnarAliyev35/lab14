package com.example.secureblog.service;

import com.example.secureblog.model.Post;
import com.example.secureblog.model.User;
import com.example.secureblog.repository.PostRepository;
import com.example.secureblog.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    private User testUser;
    private Post testPost;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");
        testUser.getRoles().add("ROLE_USER");

        testPost = new Post();
        testPost.setId(1L);
        testPost.setTitle("Test Post");
        testPost.setContent("This is a test content.");
        testPost.setUser(testUser);
    }

    @Test
    void shouldCreatePostSuccessfully() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(postRepository.save(any(Post.class))).thenReturn(testPost);

        Post savedPost = postService.createPost("Test Post", "This is a test content.", 1L);

        assertNotNull(savedPost);
        assertEquals("Test Post", savedPost.getTitle());
        assertEquals("This is a test content.", savedPost.getContent());
        assertEquals(1L, savedPost.getUser().getId());
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void shouldGetAllPosts() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(testPost));

        List<Post> posts = postService.getAllPosts();

        assertFalse(posts.isEmpty());
        assertEquals(1, posts.size());
        assertEquals("Test Post", posts.get(0).getTitle());
    }

    @Test
    void shouldGetPostsByUser() {
        when(postRepository.findByUserId(1L)).thenReturn(Arrays.asList(testPost));

        List<Post> posts = postService.getPostsByUser(1L);

        assertFalse(posts.isEmpty());
        assertEquals(1, posts.size());
        assertEquals("Test Post", posts.get(0).getTitle());
    }

    @Test
    void shouldDeletePostSuccessfully() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(testPost));
        doNothing().when(postRepository).delete(testPost);

        postService.deletePost(1L, 1L);

        verify(postRepository).delete(testPost);
    }
}
