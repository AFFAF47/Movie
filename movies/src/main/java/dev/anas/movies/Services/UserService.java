package dev.anas.movies.Services;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import dev.anas.movies.Models.User;
import dev.anas.movies.Repositories.IUserRepository;
import lombok.NonNull;
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

    public User addUser(@NonNull User user){
        return userRepository.save(user);
    }

    public boolean deleteUser(String email){
        return userRepository.deleteByEmail(email);
    }

    public Optional<User> updateUser(User updatedUser, String email){
        Optional<User> currentUser = userRepository.getUserByEmail(email);
        if(currentUser.isPresent()){
            User userToUpdate = currentUser.get();
            if(!updatedUser.getUserName().isEmpty()){
                userToUpdate.setUserName(updatedUser.getUserName());
            }
            if(!updatedUser.getPassword().isEmpty()){
                userToUpdate.setPassword(updatedUser.getPassword());
            }
            if(!updatedUser.getPhone().isEmpty()){
                userToUpdate.setPhone(updatedUser.getPhone());
            }
            User savedUser = userRepository.save(userToUpdate);
            return Optional.of(savedUser);
        }else{
            return Optional.empty();
        }
    }

}
