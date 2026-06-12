package com.example.blogapi.service;

import com.example.blogapi.dto.post.PostRequest;
import com.example.blogapi.dto.post.PostResponse;
import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.exception.UnauthorizedException;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.PostLike;
import com.example.blogapi.model.User;
import com.example.blogapi.repository.PostLikeRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public List<PostResponse> getAllPosts(String currentUser) {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(post -> mapToResponse(post, currentUser))
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id, String currentUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return mapToResponse(post, currentUser);
    }

    public PostResponse createPost(PostRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        Post post = new Post(request.getTitle(), request.getContent(), user);
        Post savedPost = postRepository.save(post);
        return mapToResponse(savedPost, username);
    }

    public PostResponse updatePost(Long id, PostRequest request, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedException("You are not authorized to update this post");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToResponse(updatedPost, username);
    }

    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedException("You are not authorized to delete this post");
        }

        postRepository.delete(post);
    }

    public void toggleLike(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        postLikeRepository.findByPostIdAndUserId(postId, user.getId())
                .ifPresentOrElse(
                        postLikeRepository::delete,
                        () -> postLikeRepository.save(new PostLike(post, user))
                );
    }

    private PostResponse mapToResponse(Post post, String currentUserUsername) {
        int likeCount = postLikeRepository.countByPostId(post.getId());
        boolean isLiked = false;

        if (currentUserUsername != null) {
            User user = userRepository.findByUsername(currentUserUsername).orElse(null);
            if (user != null) {
                isLiked = postLikeRepository.existsByPostIdAndUserId(post.getId(), user.getId());
            }
        }

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getAuthor().getUsername(),
                post.getAuthor().getId(),
                post.getComments().size(),
                likeCount,
                isLiked
        );
    }
}
