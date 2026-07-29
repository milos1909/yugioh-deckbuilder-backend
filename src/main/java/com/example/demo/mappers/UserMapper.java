package com.example.demo.mappers;

import com.example.demo.entities.User;
import com.example.demo.models.RegisterUserModel;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserPageModel;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static User toEntity(UserModel model) {
        User user = new User();
        user.setId(model.getId() == 0 ? null : model.getId());
        user.setUsername(model.getUsername());
        user.setEmail(model.getEmail());
        return user;
    }

    public static User toEntity(RegisterUserModel model, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setEmail(model.getEmail());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        return user;
    }

    public static UserModel toModel(User entity){
        return UserModel.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .decks(DeckMapper.toModelList(entity.getDecks()))
                .build();

    }

    public static List<UserModel> toModelList(List<User> entities){
        var list = new ArrayList<UserModel>();
        for (var entity : entities) {
            list.add(toModel(entity));
        }
        return list;
    }

    public static UserPageModel toModelPagedList(Page<User> pageEntity){
        return UserPageModel.builder()
                .users(toModelList(pageEntity.getContent()))
                .totalPages(pageEntity.getTotalPages())
                .totalElements(pageEntity.getTotalElements())
                .build();
    }
}
