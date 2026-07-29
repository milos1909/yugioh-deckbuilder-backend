package com.example.demo.mappers;

import com.example.demo.entities.Card;
import com.example.demo.models.CardModel;
import com.example.demo.models.CardPageModel;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class CardMapper {
    public static CardModel toModel(Card entity) {
        return CardModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .description(entity.getDescription())
                .atk(entity.getAtk())
                .def(entity.getDef())
                .level(entity.getLevel())
                .race(entity.getRace())
                .attribute(entity.getAttribute())
                .archetype(entity.getArchetype())
                .linkval(entity.getLinkval())
                .image_url_small(entity.getImage_url_small())
                .build();
    }

    public static List<CardModel> toModelList(List<Card> entities) {
        var list = new ArrayList<CardModel>();

        for (var entity : entities) {
            list.add(toModel(entity));
        }

        return list;
    }

    public static CardPageModel toModelPagedList(Page<Card> pageEntity){
        return CardPageModel.builder()
                .cards(toModelList(pageEntity.getContent()))
                .totalPages(pageEntity.getTotalPages())
                .totalElements(pageEntity.getTotalElements())
                .build();
    }
}
