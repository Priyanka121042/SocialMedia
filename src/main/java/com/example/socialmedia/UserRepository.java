package com.example.socialmedia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User1, Integer> {
    User1 findByEmailAndPassword(String email, String password);
    Optional<User1> findByUserID(int userID);
    User1 findByuserID(int userID);
    Optional<User1> findByEmail(String email);
}
