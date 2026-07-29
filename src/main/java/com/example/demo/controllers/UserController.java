package com.example.demo.controllers;

import com.example.demo.models.UserModel;
import com.example.demo.models.UserPageModel;
import com.example.demo.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final IUserService userService;

    @GetMapping("get-page-list")
    public UserPageModel getPageList(Integer pageNumber, Integer pageSize) {
        return userService.findPagedList(PageRequest.of(pageNumber, pageSize));
    }

    @PostMapping("create")
    public ResponseEntity<?> createUserBody(@RequestBody @Valid UserModel userModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Neuspesno registrovan!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(userService.create(userModel), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody @Valid UserModel userModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userService.update(userModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid UserModel userModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userService.delete(userModel);
        return new ResponseEntity<>( HttpStatus.OK );
    }

    

    
}
