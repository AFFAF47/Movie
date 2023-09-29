package dev.anas.movies.Controllers;

import dev.anas.movies.Models.User;
import dev.anas.movies.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getallUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{data}") //here data can have either phone number or email ID
    @ResponseBody
    public ResponseEntity<Optional<User>> getUserByEmailOrPhone(@PathVariable String data){
        if (data.contains("@")) {
            return new ResponseEntity<Optional<User>>(userService.getUserByEmail(data), HttpStatus.OK);
        }else if (data.matches("\\d+")){
            return new ResponseEntity<Optional<User>>(userService.getUserByPhone(data), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/adduser")
    @ResponseBody
    public User addUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED).getBody();
    }

    @DeleteMapping("/delete/{email}")
    public boolean delUser(@PathVariable String email){
        return userService.deleteUser(email);
    }

}
