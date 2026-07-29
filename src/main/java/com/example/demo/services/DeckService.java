package com.example.demo.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Card;
import com.example.demo.entities.Deck;
import com.example.demo.mappers.DeckMapper;
import com.example.demo.models.DeckModel;
import com.example.demo.models.DeckPageModel;
import com.example.demo.repositories.ICardRepository;
import com.example.demo.repositories.IDeckRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeckService implements IDeckService {

    private final IDeckRepository deckRepository;
    private final ICardRepository cardRepository;

    @Override
    public DeckPageModel findPagedList(PageRequest pageRequest, Integer userId) {
        var result = deckRepository.findDecksByUserId(userId , pageRequest);
        return DeckMapper.toModelPagedList(result);
    }

    @Override
    public DeckModel findDeckById(Integer deckId) {
        var result = deckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found"));;
        return DeckMapper.toModel(result);
    }

    @Override
    public void update(DeckModel deckModel) {
        Deck deck = deckRepository.findById(deckModel.getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        deck.setName(deckModel.getName());
        deckRepository.save(deck);


    }

    @Override
    public void delete(DeckModel deckModel) {
        Deck deck = deckRepository.findById(deckModel.getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        deck.getCards().clear();
        deckRepository.delete(deck);
    }

    @Override
    public void create(Integer userId) {
        Deck entity = new Deck();
        entity.setName("New deck");
        entity.setUserId(userId);

        deckRepository.save(entity);
    }

    @Override
    public void addCardToDeck(Integer deckId, Integer cardId) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found"));

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        deck.getCards().add(card);
        deckRepository.save(deck);
    }

    @Override
    public void removeCardFromDeck(Integer deckId, Integer cardId) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found"));

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        deck.getCards().remove(card);
        deckRepository.save(deck);
    }

}
