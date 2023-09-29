package com.Musify.Controllers;

import com.Musify.Controllers.Auth.AuthenticationRequest;
import com.Musify.Controllers.Auth.AuthenticationResponse;
import com.Musify.Controllers.Auth.AuthenticationService;
import com.Musify.DataRepositories.UserRepository;
import com.Musify.DataTables.Users;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService service;

    public UserController(UserRepository userRepository, AuthenticationManager authenticationManager, AuthenticationService service) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @GetMapping("/user/get/{requestId}")
    private ResponseEntity<Users> getUser(@PathVariable Integer requestId){
        Optional<Users> optUser= userRepository.findById(requestId);
        if(optUser.isPresent()){
            Users user=optUser.get();
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/get")
    private ResponseEntity<Iterable<Users>> getAll(){
        Iterable<Users> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user/registration")
    private ResponseEntity<Void> createUser(@RequestBody Users newUser, UriComponentsBuilder ucb){
        newUser.setDeactivated(false); //by default these should be false
        newUser.setDeleted(false);
        newUser.setRole("ROLE_REGULAR");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); //save the password hashed
        try{
            Users user=userRepository.save(newUser);
            URI locationOfNewUser = ucb.path("user/get/{id}").buildAndExpand(user.getId()).toUri(); //get the location of the new object
            return ResponseEntity.created(locationOfNewUser).build();
        }
        catch (DataIntegrityViolationException e){ // in case of unsuccesfull save, mostly cause of duplicate entry for uniq column
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/user/update/{requestedId}")
    private ResponseEntity<Void> updateUser(@PathVariable Integer requestedId, @RequestBody Users updateUser, Principal principal){
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

    @PutMapping("/user/promote/{requestedId}")
    private ResponseEntity<Void> promoteUser(@PathVariable Integer requestedId){
        Optional<Users> optUser= userRepository.findById(requestedId);
        if(optUser.isPresent()){
            Users user=optUser.get();
            user.setRole("ROLE_ADMIN");
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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
