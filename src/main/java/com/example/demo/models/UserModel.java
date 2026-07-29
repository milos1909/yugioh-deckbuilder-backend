package com.example.demo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserModel {
    private int id;

    @NotBlank
    private String username;

    @Email
    private String email;
    
    private List<DeckModel> decks;
}
