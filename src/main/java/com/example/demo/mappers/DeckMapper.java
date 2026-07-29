package com.example.demo.mappers;

import com.example.demo.entities.Deck;
import com.example.demo.models.CardModel;
import com.example.demo.models.DeckModel;
import com.example.demo.models.DeckPageModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class DeckMapper {

    private static boolean isExtraDeckType(String type) {
    return type != null && (
        type.contains("Fusion Monster") ||
        type.contains("Fusion Pendulum Effect Monster")||
        type.contains("Synchro Monster") ||
        type.contains("Synchro Pendulum Effect Monster")||
        type.contains("XYZ Monster") ||
        type.contains("XYZ Pendulum Effect Monster")||
        type.contains("Link Monster")
    );
}

    public static DeckModel toModel(Deck entity) {
        List<CardModel> cards = CardMapper.toModelList(entity.getCards());
        List<CardModel> main = new ArrayList<>();
        List<CardModel> extra = new ArrayList<>();

        for (CardModel card : cards) {
            String type = card.getType();

            if (isExtraDeckType(type)) {
                extra.add(card);
            } else {
                main.add(card);
            }
        }

        return DeckModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .userId(entity.getUserId())
                .main(main)
                .extra(extra)
                .build();

    }

    public static List<DeckModel> toModelList(List<Deck> entities) {
        var list = new ArrayList<DeckModel>();

        for (var entity : entities) {
            list.add(toModel(entity));
        }

        return list;
    }

    public static DeckPageModel toModelPagedList(Page<Deck> pageEntity){
        return DeckPageModel.builder()
                .decks(toModelList(pageEntity.getContent()))
                .totalPages(pageEntity.getTotalPages())
                .totalElements(pageEntity.getTotalElements())
                .build();
    }
}
