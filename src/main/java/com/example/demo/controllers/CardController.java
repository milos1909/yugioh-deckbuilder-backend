package com.example.demo.controllers;

import com.example.demo.models.CardPageModel;
import com.example.demo.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("card")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CardController {
    private final CardService cardService;

    @GetMapping("get-page-list")
    public CardPageModel getPageList(Integer pageNumber, String searchTerm) {
        CardPageModel cardPage = cardService.findPagedList(PageRequest.of(pageNumber, 16), searchTerm);
        cardService.getCardImages(cardPage.getCards());
        return cardPage;
    }
}