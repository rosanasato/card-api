package com.fastforward.cardapi.controller;

import com.fastforward.cardapi.controller.request.CreateOriginRequest;
import com.fastforward.cardapi.model.Origin;
import com.fastforward.cardapi.service.OriginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/origin")
public class OriginController {

    private static final Logger LOG = LoggerFactory.getLogger(OriginController.class);
    private final OriginService originService;

    public OriginController(OriginService originService) {
        this.originService = originService;
    }

    @GetMapping("{id}")
    public Origin findOriginById(@PathVariable("id") int idOrigin){
        LOG.info("Iniciando busca pela origin com id [{}]", idOrigin);

        var origin = originService.findOriginById(idOrigin);

        if (origin.isPresent()){
            return origin.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Origin não encontrada");
    }

    @PostMapping
    public Origin createOrigin(@RequestBody CreateOriginRequest createOriginRequest){
        LOG.info("Iniciando criação de origin com nome [{}]" , createOriginRequest.getName());
        return originService.createOrigin(createOriginRequest);
    }

}
