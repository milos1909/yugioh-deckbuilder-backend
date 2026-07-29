package com.example.demo.services;

import com.example.demo.models.CardPageModel;
import org.springframework.data.domain.PageRequest;

public interface ICardService {
    CardPageModel findPagedList(PageRequest pageRequest , String searchTerm);
}
