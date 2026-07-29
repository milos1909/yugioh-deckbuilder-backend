package com.example.demo.repositories;
import com.example.demo.entities.Deck;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeckRepository extends JpaRepository<Deck, Integer> {
    Page<Deck> findDecksByUserId(Integer userId , Pageable pageable);
}
