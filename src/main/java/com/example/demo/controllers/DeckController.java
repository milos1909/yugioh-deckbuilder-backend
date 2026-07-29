package com.example.demo.controllers;

import com.example.demo.models.DeckCardModel;
import com.example.demo.models.DeckModel;
import com.example.demo.models.DeckPageModel;
import com.example.demo.services.IDeckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deck")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class DeckController {
    private final IDeckService deckService;

    @GetMapping("get-page-list")
    public DeckPageModel getPageList(Integer pageNumber, Integer pageSize ,Integer userId) {
        return deckService.findPagedList(PageRequest.of(pageNumber, pageSize), userId);
    }

    @GetMapping("get-by-id")
    public DeckModel getDeckById(Integer deckId) {
        return deckService.findDeckById(deckId);
    }

    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody @Valid DeckModel deckModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

        deckService.update(deckModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid DeckModel deckModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

        deckService.delete(deckModel);
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @PostMapping("create")
    public ResponseEntity<Void> createDeck(@RequestBody Map<String, Integer> body) {
        deckService.create(body.get("userId"));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("add-card")
    public ResponseEntity<Void> addCard(@RequestBody @Valid DeckCardModel deckCardModel, BindingResult result) {
        deckService.addCardToDeck(deckCardModel.getDeckId(), deckCardModel.getCardId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("remove-card")
    public ResponseEntity<Void> removeCard(@RequestBody @Valid DeckCardModel deckCardModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

        deckService.removeCardFromDeck(deckCardModel.getDeckId(), deckCardModel.getCardId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
