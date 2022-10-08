package com.fastforward.cardapi.service;

import com.fastforward.cardapi.controller.request.CreateCardRequest;
import com.fastforward.cardapi.controller.request.UpdateCardRequest;
import com.fastforward.cardapi.exception.EntityNotFoundException;
import com.fastforward.cardapi.model.Card;
import com.fastforward.cardapi.repository.CardRepository;
import com.fastforward.cardapi.repository.OriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final OriginRepository originRepository;

    @Autowired
    public CardService(CardRepository cardRepository, OriginRepository originRepository) {
        this.cardRepository = cardRepository;
        this.originRepository = originRepository;
    }

    public Card findById(long id){
        return cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Origin de id [" + id + "] não encontrado")
                );
    }

    public Card createCard(CreateCardRequest createCardRequest){

        var origin = originRepository.findById(createCardRequest.getOriginId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Origin de id [" + createCardRequest.getOriginId() + "] não encontrado")
                );

        var card = new Card();

        card.setName(createCardRequest.getName());
        card.setDescription(createCardRequest.getDescription());
        card.setStrength(createCardRequest.getStrength());
        card.setSpeed(createCardRequest.getSpeed());
        card.setSkill(createCardRequest.getSkill());
        card.setGear(createCardRequest.getGear());
        card.setIntellect(createCardRequest.getIntellect());
        card.setImageUrl(createCardRequest.getImageUrl());
        card.setOrigin(origin);
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdatedAt(LocalDateTime.now());

        return cardRepository.save(card);
    }

    public void deleteCard(long id) {
        var card = findById(id);

        //deleta por objeto
        cardRepository.delete(card);

        //deleta por id
        //cardRepository.deleteById(id);
    }


    public Card updateCard(long id, UpdateCardRequest updateCardRequest) {
        var card = findById(id);

        card.setName(updateCardRequest.getName());
        card.setDescription(updateCardRequest.getDescription().isBlank()
                ? card.getDescription()
                : updateCardRequest.getDescription());
        card.setStrength(updateCardRequest.getStrength());
        card.setSpeed(updateCardRequest.getSpeed());
        card.setSkill(updateCardRequest.getSkill());
        card.setGear(updateCardRequest.getGear());
        card.setIntellect(updateCardRequest.getIntellect());
        card.setImageUrl(updateCardRequest.getImageUrl());

        if (updateCardRequest.getOriginId() != 0L
                && updateCardRequest.getOriginId() != card.getOrigin().getId()){

            var origin = originRepository.findById(updateCardRequest.getOriginId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Origin de id [" + id + "] não encontrado")
                    );

            card.setOrigin(origin);
        }

        return cardRepository.save(card);

    }
}
