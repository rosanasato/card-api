package com.fastforward.cardapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

    private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

    //GET localhost:8080/health
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/health", produces = "application/json")
    public String health(){
        LOG.info("Avaliando saúde da aplicação");
        return "{\"status\":\"ok\"}";
    }
}
