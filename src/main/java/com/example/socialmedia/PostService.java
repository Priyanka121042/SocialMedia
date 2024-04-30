package com.example.socialmedia;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Post1 createPost(PostRequest postRequest) {
        Post1 post = new Post1(postRequest.getPostBody(), postRequest.getUserID());
        return postRepository.save(post);
    }

    public PostResponse getPost(int postID) {
        Optional<Post1> post = postRepository.findById(postID);
        if(post.isPresent()){
            List<Comment1> comments = commentRepository.findAllBypostID(postID);
            List<CommentResponse> commentResponses = new ArrayList<>();
            for(Comment1 comment : comments){
                User1 commentCreator = userRepository.findByuserID(comment.getUserID());
                UserResponse response3 = new UserResponse(commentCreator.getUserID(), commentCreator.getName());
                CommentResponse response2 = new CommentResponse(comment.getUserID(), comment.getCommentBody(),response3);
                commentResponses.add(response2);
            }

            PostResponse response1 = new PostResponse(postID, post.get().getPostBody(), post.get().getDate(), commentResponses);
            return response1;
        }
        else
        {
            throw new NoSuchElementException("Post does not exist");
        }
    }

    public void editPost(String postBody, int postID) {
        Optional<Post1> post = postRepository.findById(postID);
        if (post.isPresent()) {
            Post1 postToUpdate = post.get();
            postToUpdate.setPostBody(postBody);
            postRepository.save(postToUpdate);
        } else {
            throw new EntityNotFoundException("Post does not exist");
        }
    }

    public void deletePost(int postID) {
        Optional<Post1> post = postRepository.findById(postID);
        if (post.isPresent()) {
            postRepository.delete(post.get());
        }
        else {
            throw new EntityNotFoundException("Post does not exist");
        }
    }

    public boolean isPostExists(int postID) {
        return postRepository.existsById(postID);
    }
}

