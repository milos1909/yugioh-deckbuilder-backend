package com.example.demo.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardModel {
    private Integer id;
    private String name;
    private String type;
    private String description;
    private Integer atk;
    private Integer def;
    private Integer level;
    private String race;
    private String attribute;
    private String archetype;
    private Integer linkval ;
    private String image_url_small;
}
