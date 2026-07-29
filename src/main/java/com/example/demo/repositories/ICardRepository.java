package com.example.demo.repositories;

import com.example.demo.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository<Card, Integer> {
    Page<Card> findCardByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}
