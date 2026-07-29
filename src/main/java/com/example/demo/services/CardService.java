package com.example.demo.services;

import com.example.demo.mappers.CardMapper;
import com.example.demo.models.CardModel;
import com.example.demo.models.CardPageModel;
import com.example.demo.repositories.ICardRepository;
import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    private final ICardRepository cardRepository;

    @Override
    public CardPageModel findPagedList(PageRequest pageRequest, String searchTerm) {
        var result = cardRepository.findCardByNameContainingOrDescriptionContaining(searchTerm, searchTerm, pageRequest);
        return CardMapper.toModelPagedList(result);
    }

    public void getCardImages(List<CardModel> cards){
        RestTemplate restTemplate = new RestTemplate();

        for(CardModel card : cards){
            String imageUrl = card.getImage_url_small();
            Path imagePath = Paths.get("../front-end/public/images", card.getId() + ".jpg");

            if (!Files.exists(imagePath)) {
                try{
                    byte[] imageBytes = restTemplate.getForObject(imageUrl, byte[].class);
                    Files.write(imagePath, imageBytes);
                }catch (ResourceAccessException e) {
                    System.err.println("Network error downloading image: " + e.getMessage());
                }
                catch(IOException e){
                    System.err.println("Error saving the image: " + e.getMessage());
                }
            } 
        }
    }
}
