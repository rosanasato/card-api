package com.fastforward.cardapi.controller;

import com.fastforward.cardapi.model.Card;
import com.fastforward.cardapi.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/card")
public class CardController{

    // controller >> service >> repository >> model
    private static final Logger LOG = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    //GET localhost:8080/card/health
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/health", produces = "application/json")
    public String health(){
        LOG.info("Avaliando saúde da aplicação");
        return "{\"status\":\"ok\"}";
    }

    //GET localhost:8080/card/{id}
    @GetMapping("{id}")
    public Card findCardById(@PathVariable("id") int id){
        LOG.info("Iniciando busca pelo card com id [{}]", id);

        var card = cardService.findById(id);

        if (card.isPresent()){
            return card.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card não encontrado");
    }

}
