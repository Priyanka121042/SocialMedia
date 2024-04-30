package com.example.socialmedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.RuntimeException;
import java.util.Optional;

class UserDetails {
    private int userID;
    private String name;
    private String email;

    public UserDetails() {}

    public UserDetails(int userID, String name, String email) {
        this.name = name;
        this.userID = userID;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User1 login(String email, String password) {
        Optional<User1> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (!user.get().getPassword().equals(password)) {
                throw new IncorrectPasswordException("Username/Password Incorrect");
            }
            return user.get();
        }
        else {
            throw new UserNotFoundException("User does not exist");
        }
    }

    public User1 signup(String email, String name, String password) {
        Optional<User1> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Forbidden, Account already exists");
        }
        // Create and save the new user
        User1 user1 = new User1(email, name, password); // Assuming User1 constructor exists
        userRepository.save(user1);
        return user1;
    }

    public UserDetails getUser(int userID) {
        Optional<User1> user = userRepository.findById(userID);
        if (user.isPresent()) {
            UserDetails userDetails = new UserDetails(user.get().getUserID(), user.get().getName(), user.get().getEmail());
            return userDetails;
        }
        else
        {
            throw new UserNotFoundException("User does not exist");
        }
    }

    public boolean isUserExists(int userID) {
        return userRepository.existsById(userID);
    }
}

// Definition of UserNotFoundException
class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

// Definition of UserAlreadyExistsException
class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}