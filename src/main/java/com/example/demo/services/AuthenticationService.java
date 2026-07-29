package com.example.demo.services;

import com.example.demo.exceptions.user.UserAlreadyExistException;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.LoginResponseModel;
import com.example.demo.models.LoginUserModel;
import com.example.demo.models.RegisterUserModel;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.IRoleRepository;
import com.example.demo.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserModel signup(RegisterUserModel model) {
        var user = UserMapper.toEntity(model, passwordEncoder);
        
        var existingUser = userRepository.findByUsername(model.getUsername());

        if (existingUser != null) {
            throw new UserAlreadyExistException("User " + model.getUsername() + " already exists");
        }

        user.setRoles(List.of(roleRepository.findByName("User")));
        var savedUser = userRepository.save(user);

        return UserMapper.toModel(savedUser);

    }

    public LoginResponseModel authenticate(LoginUserModel model) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        model.getUsername(),
                        model.getPassword()
                )
        );

        var authenticatedUser = userRepository.findByUsername(model.getUsername());

        if (authenticatedUser == null){
            throw new UsernameNotFoundException("User " + model.getUsername() + " not found");
        }
                
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseModel loginResponse = new LoginResponseModel();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
    }
}
