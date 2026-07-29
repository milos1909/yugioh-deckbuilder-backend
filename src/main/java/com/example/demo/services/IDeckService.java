package com.example.demo.services;

import org.springframework.data.domain.PageRequest;

import com.example.demo.models.DeckModel;
import com.example.demo.models.DeckPageModel;

public interface IDeckService {
    DeckPageModel findPagedList(PageRequest pageRequest , Integer userId);

    DeckModel findDeckById(Integer deckId);

    void update(DeckModel model);

    void delete(DeckModel model);

    void create(Integer userId);

    void addCardToDeck(Integer deckId, Integer cardId);

    void removeCardFromDeck(Integer deckId, Integer cardId);
} 
