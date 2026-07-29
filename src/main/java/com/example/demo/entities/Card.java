package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "card")
@Data
public class Card {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "`desc`" , columnDefinition = "TEXT")
    private String description;

    @Column(name = "atk")
    private Integer atk;

    @Column(name = "def")
    private Integer def;

    @Column(name = "level")
    private Integer level;

    @Column(name = "race")
    private String race;

    @Column(name = "attribute")
    private String attribute;

    @Column(name = "archetype")
    private String archetype;

    @Column(name = "linkval")
    private Integer linkval ;

    @Column(name = "image_url_small")
    private String image_url_small;

    @ManyToMany(mappedBy = "cards")
    @JsonBackReference
    private List<Deck> decks;
}
