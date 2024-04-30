package com.example.socialmedia;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
    @RequestMapping("/post")
    public class PostController {

    @Autowired
    private PostService postService;
    private UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        // Check if the user exists
        if (!userService.isUserExists(postRequest.getUserID())) {
            ErrorClass error = new ErrorClass("User does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // Create post
        Post1 createdPost = postService.createPost(postRequest);

        return ResponseEntity.ok().body("Post created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getPost(@RequestParam int postID) {
        try{
            PostResponse postDetails = postService.getPost(postID);
            return ResponseEntity.ok().body(postDetails);
        }
        catch(Exception e){
            ErrorClass error = new ErrorClass(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PatchMapping
    public ResponseEntity<?> editPost(@RequestBody EditRequest editRequest) {
        try {
            postService.editPost(editRequest.getpostBody(), editRequest.getpostID());
            return ResponseEntity.ok("Post edited successfully");
        }
        catch (Exception e) {
            ErrorClass error = new ErrorClass(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam int postID) {
        try{
            postService.deletePost(postID);
            return ResponseEntity.ok("Post deleted");
        }
        catch (Exception e) {
            ErrorClass error = new ErrorClass(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
