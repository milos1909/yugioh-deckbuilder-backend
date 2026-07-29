package com.example.demo.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardPageModel {
    private List<CardModel> cards;
    private int totalPages;
    private Long totalElements;
}
