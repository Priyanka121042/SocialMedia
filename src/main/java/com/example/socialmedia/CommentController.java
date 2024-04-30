package com.example.socialmedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    CommentController(){};

    CommentController(CommentService commentService, UserService userService, PostService postService){
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
        // Check if the user exists
        if (!userService.isUserExists(commentRequest.getUserID())) {
            ErrorClass error = new ErrorClass("User does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (!postService.isPostExists(commentRequest.getPostID())) {
            ErrorClass error = new ErrorClass("Post does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        Comment1 createdComment = commentService.createComment(commentRequest);

        return ResponseEntity.ok().body("Comment created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getComment(@RequestParam int CommentID) {
        try{
            CommentResponse commentDetails = commentService.getComment(CommentID);
            return ResponseEntity.ok().body(commentDetails);
        }
        catch(Exception e){
            ErrorClass error = new ErrorClass(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PatchMapping
    public ResponseEntity<?> editComment(@RequestBody EditcRequest editcRequest) {
        try {
            commentService.editComment(editcRequest.getCommentBody(), editcRequest.getCommentID());
            return ResponseEntity.ok("Comment edited successfully");
        }
        catch (Exception e) {
            ErrorClass error = new ErrorClass(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam int commentID) {
        try{
            commentService.deleteComment(commentID);
            return ResponseEntity.ok("Comment deleted");
        }
        catch (Exception e) {
            ErrorClass error = new ErrorClass(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
