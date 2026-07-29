package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.Deck;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserPageModel;
import com.example.demo.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public UserPageModel findPagedList(PageRequest pageRequest) {
        var result = userRepository.findAll(pageRequest);
        return UserMapper.toModelPagedList(result);
    }

    @Override
    public UserModel create(UserModel model) {
        var entity = UserMapper.toEntity(model);
        return UserMapper.toModel(userRepository.save(entity));
    }

    @Override
    public void update(UserModel userModel) {
        User user = userRepository.findById(userModel.getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());

        userRepository.save(user);


    }

    @Override
    public void delete(UserModel userModel) {
        User user = userRepository.findById(userModel.getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        user.getRoles().clear();    
        userRepository.delete(user);
    }
}
