package com.fastforward.cardapi.repository.impl;

import com.fastforward.cardapi.exception.InvalidEntityException;
import com.fastforward.cardapi.model.Origin;
import com.fastforward.cardapi.repository.OriginRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OriginRepositoryImpl implements OriginRepository {

    private List<Origin> origins;

    public  OriginRepositoryImpl(){
        origins = new ArrayList<>();

        var origin = new Origin();
        origin.setId(1);
        origin.setName("Marvel");
        origin.setDescription("Comics");
        origin.setCreator("Stan Lee");
        origin.setCreatedAt(LocalDateTime.now());
        origin.setUpdatedAt(LocalDateTime.now());

        origins.add(origin);
    }

    @Override
    public Optional<Origin> findOriginById(int id) {

        return origins.stream()
                .filter(origin -> origin.getId() == id)
                .findFirst();
    }

    @Override
    public Origin save(Origin origin) {
        var originFound = origins.stream()
                .filter(originInList -> originInList.getName().equals(origin.getName()))
                .findFirst();

        if (originFound.isPresent()){
            throw new InvalidEntityException("A Origin com nome [" + origin.getName() + "] já existe");
        }

        origin.setId(origins.size()+1);
        origins.add(origin);
        return origin;
    }
}
