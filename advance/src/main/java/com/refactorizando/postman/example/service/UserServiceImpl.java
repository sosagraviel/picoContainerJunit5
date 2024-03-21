package com.refactorizando.postman.example.service;

import com.refactorizando.postman.example.domain.user.UserApp;
import com.refactorizando.postman.example.dto.LoginRequest;
import com.refactorizando.postman.example.dto.SignupRequest;
import com.refactorizando.postman.example.dto.UserDTO;
import com.refactorizando.postman.example.exeptions.ErrorConstants;
import com.refactorizando.postman.example.exeptions.MissingUserException;
import com.refactorizando.postman.example.exeptions.UserExistException;
import com.refactorizando.postman.example.mapper.UserMapper;
import com.refactorizando.postman.example.repository.UserRepository;
import com.refactorizando.postman.example.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public UserApp registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            throw new UserExistException(ErrorConstants.EXIST_USER);
        } else {
            UserApp user =
                    UserApp.builder()
                            .userName(signUpRequest.getUsername())
                            .roles(signUpRequest.getRoles())
                            .password(passwordEncoder.encode(signUpRequest.getPassword()))
                            .build();
            return userRepository.save(user);
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtToken(authentication);
    }

    @Override
    public UserDTO getAccount() {
        return null;
    }

    @Override
    public UserDTO getAccountByEmail(String email) {
        return null;
    }

    @Override
    public List<UserDTO> getUser() {
        if (!UserMapper.INSTANCE.toUserDTO(userRepository.findAll()).isEmpty()) {
            return UserMapper.INSTANCE.toUserDTO(userRepository.findAll());
        } else {
            throw new MissingUserException(ErrorConstants.MISSING_USER);
        }
    }
}
