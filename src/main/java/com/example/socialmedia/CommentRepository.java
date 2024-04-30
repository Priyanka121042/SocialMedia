package com.example.socialmedia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment1, Integer> {
    Comment1 findBycommentID(int commentID);
    List<Comment1> findAllBypostID(int postID);
    //List<Comment1> findAllBypostByOrderOfcommentIDDesc(int postID);
}
