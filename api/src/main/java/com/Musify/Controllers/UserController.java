package com.Musify.Controllers;

import com.Musify.DataRepositories.UserRepository;
import com.Musify.DataTables.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/registration")
    private ResponseEntity<Void> createUser(@RequestBody Users newUser, UriComponentsBuilder ucb){
        Users user=userRepository.save(newUser);
        URI locationOfNewUser = ucb.path("/{id}").buildAndExpand(user.getId()).toUri(); //get the location of the new object
        return ResponseEntity.created(locationOfNewUser).build();
    }
}
