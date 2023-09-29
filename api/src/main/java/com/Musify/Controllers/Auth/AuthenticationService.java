package com.Musify.Controllers.Auth;

import com.Musify.DataRepositories.UserRepository;
import com.Musify.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository repository;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository repository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.repository = repository;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByUserName(request.getEmail());
        var jwtToken = jwtService.generateToken(user.getEmail());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
