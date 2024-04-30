package com.example.socialmedia;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    CommentService(){};

    CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Comment1 createComment(CommentRequest commentRequest) {
        Comment1 comment = new Comment1(commentRequest.getcommentBody(), commentRequest.getPostID(), commentRequest.getUserID());
        return commentRepository.save(comment);
    }

    public CommentResponse getComment(int commentID){
        Optional<Comment1> comment = commentRepository.findById(commentID);
        if(comment.isPresent()){
            User1 commentCreator = userRepository.findByuserID(comment.get().getUserID());
            UserResponse response2 = new UserResponse(commentCreator.getUserID(), commentCreator.getName());
            CommentResponse response1 = new CommentResponse(comment.get().getUserID(), comment.get().getCommentBody(),response2);
            return response1;
        }
        else
        {
            throw new NoSuchElementException("Comment does not exist");
        }
    }

    public void editComment(String commentBody, int commentID) {
        Optional<Comment1> comment = commentRepository.findById(commentID);
        if (comment.isPresent()) {
            Comment1 commentToUpdate = comment.get();
            commentToUpdate.setCommentBody(commentBody); // Only modifying postBody
            commentRepository.save(commentToUpdate); // Save the changes
        } else {
            throw new EntityNotFoundException("Comment does not exist");
        }
    }

    public void deleteComment(int commentID) {
        Optional<Comment1> comment = commentRepository.findById(commentID);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
        }
        else {
            throw new EntityNotFoundException("Comment doest not exist");
        }
    }

}
