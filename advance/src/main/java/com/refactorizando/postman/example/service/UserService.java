package com.refactorizando.postman.example.service;


import com.refactorizando.postman.example.domain.user.UserApp;
import com.refactorizando.postman.example.dto.LoginRequest;
import com.refactorizando.postman.example.dto.SignupRequest;
import com.refactorizando.postman.example.dto.UserDTO;

import java.util.List;


public interface UserService {

    UserApp registerUser(SignupRequest signUpRequest);
    String login(LoginRequest loginRequest);
    UserDTO getAccount();
    UserDTO getAccountByEmail(String email);
    List<UserDTO> getUser();
}
