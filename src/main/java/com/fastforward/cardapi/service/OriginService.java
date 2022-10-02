package com.fastforward.cardapi.service;

import com.fastforward.cardapi.model.Origin;
import com.fastforward.cardapi.repository.OriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OriginService {

    private final OriginRepository originRepository;

    @Autowired
    public OriginService(OriginRepository originRepository) {
        this.originRepository = originRepository;
    }

    public Optional<Origin> findOriginById(int id){
        return originRepository.findOriginById(id);
    }
}
