package com.Musify.Controllers;

import com.Musify.DataRepositories.UserRepository;
import com.Musify.DataTables.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/registration")
    private ResponseEntity<Void> createUser(@RequestBody Users newUser, UriComponentsBuilder ucb){
        newUser.setDeactivated(false); //by default these should be false
        newUser.setDeleted(false);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); //save the password hashed
        Users user=userRepository.save(newUser);
        URI locationOfNewUser = ucb.path("/{id}").buildAndExpand(user.getId()).toUri(); //get the location of the new object
        return ResponseEntity.created(locationOfNewUser).build();
    }

    @PutMapping("/user/update/{requestedId}")
    private ResponseEntity<Void> updateUser(@PathVariable Integer requestedId, @RequestBody Users updateUser, Principal principal){
        System.out.println(principal.getName());
        Optional<Users> optUser= Optional.ofNullable(userRepository.findByUserId(requestedId, principal.getName()));
        if(optUser.isPresent()){
            Users user=optUser.get();
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            user.setEmail(updateUser.getEmail());
            user.setCountry(updateUser.getCountry());
            userRepository.save(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/user/inactivate/{requestedId}")
    private ResponseEntity<Void> inactivateUser(@PathVariable Integer requestedId, Principal principal){
        Optional<Users> optUser= Optional.ofNullable(userRepository.findByUserId(requestedId, principal.getName()));
        if(optUser.isPresent()){
            Users user=optUser.get();
            user.setDeactivated(true);
            userRepository.save(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/delete/{requestedId}")
    private ResponseEntity<Void> deleteUser(@PathVariable Integer requestedId, Principal principal){
        Optional<Users> optUser= Optional.ofNullable(userRepository.findByUserId(requestedId, principal.getName()));
        if(optUser.isPresent()){
            Users user=optUser.get();
            user.setDeleted(true);
            userRepository.save(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/login")
    private void login(){
    }

}
