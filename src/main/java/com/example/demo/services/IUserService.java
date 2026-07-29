package com.example.demo.services;

import com.example.demo.models.UserModel;
import com.example.demo.models.UserPageModel;
import org.springframework.data.domain.PageRequest;

public interface IUserService {
    UserPageModel findPagedList(PageRequest pageRequest);

    UserModel create(UserModel model);

    void update(UserModel model);

    void delete(UserModel model);
}