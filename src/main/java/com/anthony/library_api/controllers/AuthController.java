package com.anthony.library_api.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anthony.library_api.models.dtos.auth.LoginDTO;
import com.anthony.library_api.models.dtos.auth.RegisterDTO;
import com.anthony.library_api.models.entities.User;
import com.anthony.library_api.security.JwtService;
import com.anthony.library_api.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager,
                          JwtService jwtService,
                          UserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDTO body) {
    	return userService.create(body);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO body) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
            		body.email(), body.password()
            )
        );

        User user = userService.findByEmail(body.email());
        
        if (user == null)
        	throw new RuntimeException("User not found.");
        
        return jwtService.generateToken(user);
    }
}

