package com.example.demo.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeckModel {
    private Integer id;
    private String name;
    private Integer userId;
    private List<CardModel> main;
    private List<CardModel> extra;

}
