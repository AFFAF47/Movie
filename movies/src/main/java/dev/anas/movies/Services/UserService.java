package dev.anas.movies.Services;

import dev.anas.movies.Models.User;
import dev.anas.movies.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public Optional<User> getUserByPhone(String phone){
        return userRepository.getUserByPhone(phone);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

}
