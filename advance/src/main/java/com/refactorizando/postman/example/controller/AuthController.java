package com.refactorizando.postman.example.controller;

import com.refactorizando.postman.example.domain.user.UserApp;
import com.refactorizando.postman.example.dto.*;
import com.refactorizando.postman.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(JwtResponse.builder()
                .token(userService.login(loginRequest))
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserApp> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(signUpRequest));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> listUser() {
        return ResponseEntity.ok(userService.getUser());
    }
}
