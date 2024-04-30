package com.example.socialmedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok("Login Successful");
        } catch (Exception e) {
            ErrorClass error = new ErrorClass(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

@PostMapping("/signup")
public ResponseEntity<?> login(@RequestBody SignupRequest signUpRequest) {
    try {
        userService.signup(signUpRequest.getEmail(), signUpRequest.getName(), signUpRequest.getPassword());
        return ResponseEntity.ok("Account Creation Successful");
    } catch (Exception e) {
        ErrorClass error = new ErrorClass(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam("userID") int userID) {
        try {
            UserDetails userDetails = userService.getUser(userID);
            /*UserDetails userDetails = new UserDetails(user.getEmail(), user.getName(), user.getPassword());*/
            return ResponseEntity.ok(userDetails);
        } catch (Exception e) {
            ErrorClass error = new ErrorClass(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserFeed() {
        List<PostResponse> userfeed = new ArrayList<>();
      List<Post1> posts = postRepository.findAll();
        Collections.sort(posts, Comparator.comparingInt(Post1::getPostID).reversed());
          for (Post1 post : posts) {
              int postID = post.getPostID();
              List<Comment1> comments = commentRepository.findAllBypostID(postID);
              List<CommentResponse> commentResponses = new ArrayList<>();
              for(Comment1 comment : comments){
                  User1 commentCreator = userRepository.findByuserID(comment.getUserID());
                  UserResponse response3 = new UserResponse(commentCreator.getUserID(), commentCreator.getName());
                  CommentResponse response2 = new CommentResponse(comment.getUserID(), comment.getCommentBody(),response3);
                  commentResponses.add(response2);
              }
              PostResponse response1 = new PostResponse(postID, post.getPostBody(), post.getDate(), commentResponses);
              userfeed.add(response1);
          }
          return ResponseEntity.ok(userfeed);
      }

    @GetMapping("/users")
    public List<UserDetails> getAllUsers() {
        List<User1> users = userRepository.findAll();
        List<UserDetails> userDetails = new ArrayList<>();
        for (User1 user : users) {
            UserDetails userDetailsI = new UserDetails(user.getUserID(), user.getName(),  user.getEmail());
            userDetails.add(userDetailsI);
        }
        return userDetails;
    }
}