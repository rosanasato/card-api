package com.fastforward.cardapi.repository;

import com.fastforward.cardapi.model.Card;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository {
    Optional<Card> findById(int id);
}
